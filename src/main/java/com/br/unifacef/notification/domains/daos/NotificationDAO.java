package com.br.unifacef.notification.domains.daos;

import com.br.unifacef.notification.domains.documents.Notification;
import com.br.unifacef.notification.domains.repositories.mongo.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationDAO {

    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }
}
