package com.br.unifacef.notification.queue.consumers;

import com.br.unifacef.notification.usecases.HandlePaymentMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificationPaymentQueueConsumer {

    private final HandlePaymentMessage handlePaymentMessage;

    @RabbitListener(queues="notificationPaymentQueue")
    public void receiveMessage(String scheduleId) throws Exception{
        try {
            log.debug("Receive message from Queue: notificationPaymentQueue. Message: {}", scheduleId);
            handlePaymentMessage.handle(Integer.valueOf( scheduleId));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
