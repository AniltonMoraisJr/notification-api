package com.br.unifacef.notification.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

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
    private Double amount;

    @Column(name = "paymentType")
    private String paymentType;

    @Column(name = "paymentCode")
    private String paymentCode;
}
