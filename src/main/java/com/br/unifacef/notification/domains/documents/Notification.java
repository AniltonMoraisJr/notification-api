package com.br.unifacef.notification.domains.documents;

import com.br.unifacef.notification.domains.documents.enums.NotificationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Builder
@Data
@Document("notification")
public class Notification {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String email;

    private String to;

    private boolean success;

    private Date createAt;
}
