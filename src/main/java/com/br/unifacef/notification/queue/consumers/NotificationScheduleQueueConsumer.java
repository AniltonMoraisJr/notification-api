package com.br.unifacef.notification.queue.consumers;

import com.br.unifacef.notification.usecases.HandlePaymentMessage;
import com.br.unifacef.notification.usecases.HandleScheduleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificationScheduleQueueConsumer {

    private final HandleScheduleMessage handleScheduleMessage;

    @RabbitListener(queues="notificationScheduleQueue")
    public void receiveMessage(Integer scheduleId) throws Exception{
        log.debug("Receive message from Queue: notificationScheduleQueue. Message: {}", scheduleId);
        try {
            handleScheduleMessage.handle(scheduleId);
        } catch (Exception e) {
            throw e;
        }
    }
}
