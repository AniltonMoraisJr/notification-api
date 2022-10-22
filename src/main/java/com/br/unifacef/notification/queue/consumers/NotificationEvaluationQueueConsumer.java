package com.br.unifacef.notification.queue.consumers;

import com.br.unifacef.notification.usecases.HandleEvaluationMessage;
import com.br.unifacef.notification.usecases.HandlePaymentMessage;
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
    public void receiveMessage(String  scheduleId) throws Exception{
        log.debug("Receive message from Queue: notificationEvaluationQueue. Message: {}", scheduleId);
        try {
            handleEvaluationMessage.handle(Integer.valueOf( scheduleId));
        } catch (Exception e) {
            throw e;
        }
    }
}
