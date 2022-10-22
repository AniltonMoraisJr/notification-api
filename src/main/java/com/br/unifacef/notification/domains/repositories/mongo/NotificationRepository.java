package com.br.unifacef.notification.domains.repositories.mongo;

import com.br.unifacef.notification.domains.documents.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
