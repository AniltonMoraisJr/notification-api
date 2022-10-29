package com.br.unifacef.notification.usecases;

import com.br.unifacef.notification.domains.dto.EmailEvaluationDto;
import com.br.unifacef.notification.domains.dto.EmailSchedulerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class SendEvaluationEmail {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${app.mailFrom}")
    private String mailFrom;

    public void send(EmailEvaluationDto emailEvaluationDto, String email){
        try {
            log.info("Sending E-mail...");

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();

            Map<String, Object> emailBodyParameters = new HashMap<>();
            emailBodyParameters.put("userName", emailEvaluationDto.getUserName());
            emailBodyParameters.put("startDate", emailEvaluationDto.getStartDate());
            emailBodyParameters.put("startHour", emailEvaluationDto.getStartHour());
            emailBodyParameters.put("endDate", emailEvaluationDto.getEndDate());
            emailBodyParameters.put("endHour", emailEvaluationDto.getEndHour());
            emailBodyParameters.put("amount", emailEvaluationDto.getAmount());
            context.setVariables(emailBodyParameters);

            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject("Estamos ansiosos pelo seu feedBack!");
            String html = templateEngine.process("notification-evaluation.html", context);
            helper.setText(html, true);

            emailSender.send(message);
        } catch (Exception e){
            log.error("Error when try send email. Error: {}", e.getMessage());
        }
    }
}
