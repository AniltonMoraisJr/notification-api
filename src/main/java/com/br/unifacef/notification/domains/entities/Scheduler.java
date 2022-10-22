package com.br.unifacef.notification.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
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
    private Double amount;

    @Column(name="startedAt")
    private Date startedAt;

    @Column(name="finishedAt")
    private Date finishedAt;
}
