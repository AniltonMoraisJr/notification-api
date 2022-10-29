package com.br.unifacef.notification.usecases;

import com.br.unifacef.notification.domains.dto.EmailEvaluationDto;
import com.br.unifacef.notification.domains.dto.EmailPaymentDto;
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
public class SendPaymentEmail {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${app.mailFrom}")
    private String mailFrom;

    public void send(EmailPaymentDto emailPaymentDto, String email){
        try {
            log.info("Sending E-mail...");

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();

            Map<String, Object> emailBodyParameters = new HashMap<>();
            emailBodyParameters.put("userName", emailPaymentDto.getUserName());
            emailBodyParameters.put("paymentType", emailPaymentDto.getPaymentType());
            emailBodyParameters.put("paymentCode", emailPaymentDto.getPaymentCode());
            emailBodyParameters.put("receiveDate", emailPaymentDto.getReceiveDate());
            emailBodyParameters.put("amount", emailPaymentDto.getAmount());
            context.setVariables(emailBodyParameters);

            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject("Recebemos seu pagamento!");
            String html = templateEngine.process("notification-payment.html", context);
            helper.setText(html, true);

            emailSender.send(message);
        } catch (Exception e){
            log.error("Error when try send email. Error: {}", e.getMessage());
        }
    }
}
