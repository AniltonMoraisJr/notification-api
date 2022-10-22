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
@Entity(name = "scheduler")
public class Scheduler implements Serializable {

    @Id
    private Integer id;

    @Column(name="userId")
    private Integer userId;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="startedAt")
    private Date startedAt;

    @Column(name="finishedAt")
    private Date finishedAt;
}
