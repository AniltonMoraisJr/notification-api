package com.br.unifacef.notification.usecases;

import com.br.unifacef.notification.domains.daos.NotificationDAO;
import com.br.unifacef.notification.domains.daos.PaymentDAO;
import com.br.unifacef.notification.domains.daos.SchedulerDAO;
import com.br.unifacef.notification.domains.daos.UserDAO;
import com.br.unifacef.notification.domains.documents.Notification;
import com.br.unifacef.notification.domains.documents.enums.NotificationType;
import com.br.unifacef.notification.domains.dto.EmailEvaluationDto;
import com.br.unifacef.notification.domains.dto.EmailPaymentDto;
import com.br.unifacef.notification.domains.entities.Payment;
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
public class HandlePaymentMessage {
    private final SendPaymentEmail sendPaymentEmail;

    private final PaymentDAO paymentDAO;

    private final SchedulerDAO schedulerDAO;

    private final UserDAO userDAO;

    private  final NotificationDAO notificationDAO;
    public void handle(Integer paymentId){
        try {
            Payment payment = paymentDAO.findById(paymentId).orElse(null);

            if (payment != null){
                Scheduler scheduler = schedulerDAO.findById(payment.getScheduleId()).orElse(null);
                User user = userDAO.findById(scheduler.getUserId()).orElseThrow();

                EmailPaymentDto dto = EmailPaymentDto
                        .builder()
                        .userName(String.format("%s %s", user.getFirstName(), user.getLastName()))
                        .paymentType(payment.getPaymentType())
                        .paymentCode(payment.getPaymentCode())
                        .receiveDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())))
                        .amount(payment.getAmount())
                        .build();

                sendPaymentEmail.send(dto, user.getEmail());

                Notification notification = Notification.builder()
                        .email(user.getEmail())
                        .success(true)
                        .type(NotificationType.payment)
                        .createAt(new Date(System.currentTimeMillis())).build();

                notificationDAO.save(notification);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
