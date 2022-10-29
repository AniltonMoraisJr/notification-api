package com.br.unifacef.notification.usecases;

import com.br.unifacef.notification.domains.daos.NotificationDAO;
import com.br.unifacef.notification.domains.daos.SchedulerDAO;
import com.br.unifacef.notification.domains.daos.UserDAO;
import com.br.unifacef.notification.domains.documents.Notification;
import com.br.unifacef.notification.domains.documents.enums.NotificationType;
import com.br.unifacef.notification.domains.dto.EmailEvaluationDto;
import com.br.unifacef.notification.domains.dto.EmailSchedulerDto;
import com.br.unifacef.notification.domains.entities.Scheduler;
import com.br.unifacef.notification.domains.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Component
@RequiredArgsConstructor
public class HandleEvaluationMessage {
    private final SendEvaluationEmail sendEvaluationEmail;

    private final SchedulerDAO schedulerDAO;

    private final UserDAO userDAO;

    private  final NotificationDAO notificationDAO;

    public void handle(Integer schedulerId){
        try {
            Scheduler scheduler = schedulerDAO.findById(schedulerId).orElse(null);

            if (scheduler != null){
                User user = userDAO.findById(scheduler.getUserId()).orElseThrow();

                EmailEvaluationDto dto = EmailEvaluationDto
                        .builder()
                        .userName(String.format("%s %s", user.getFirstName(), user.getLastName()))
                        .startDate(new SimpleDateFormat("dd/MM/yyyy").format(scheduler.getStartedAt()))
                        .endDate(new SimpleDateFormat("dd/MM/yyyy").format(scheduler.getFinishedAt()))
                        .startHour(new SimpleDateFormat("HH24:mm").format(scheduler.getStartedAt()))
                        .endHour(new SimpleDateFormat("HH24:mm").format(scheduler.getFinishedAt()))
                        .build();

                sendEvaluationEmail.send(dto, user.getEmail());

                Notification notification = Notification.builder()
                        .email(user.getEmail())
                        .success(true)
                        .type(NotificationType.evaluation)
                        .createAt(new Date(System.currentTimeMillis())).build();

                notificationDAO.save(notification);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
