package com.br.unifacef.notification.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment")
public class Payment implements Serializable {

    @Id
    private Integer id;

    @Column(name = "scheduleId")
    private Integer scheduleId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "paymentType")
    private String paymentType;

    @Column(name = "paymentCode")
    private String paymentCode;

    @Column(name = "createdAt")
    private Date createdAt;
}
