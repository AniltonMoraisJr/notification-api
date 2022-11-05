package com.br.unifacef.notification.queue.consumers;

import com.br.unifacef.notification.domains.dto.QueueEvaluationDto;
import com.br.unifacef.notification.usecases.HandleEvaluationMessage;
import com.br.unifacef.notification.usecases.HandlePaymentMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificationEvaluationQueueConsumer {

    private final HandleEvaluationMessage handleEvaluationMessage;

    @RabbitListener(queues="notificationEvaluationQueue")
    public void receiveMessage(String sdto) throws Exception{
        try {
            QueueEvaluationDto dto = new ObjectMapper().readValue(sdto, QueueEvaluationDto.class);
            log.debug("Receive message from Queue: notificationEvaluationQueue. Message: {}", dto.getScheduleId());
            handleEvaluationMessage.handle(dto.getScheduleId());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
