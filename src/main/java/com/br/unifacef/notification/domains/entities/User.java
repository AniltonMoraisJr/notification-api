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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User implements Serializable {
    @Id
    private Integer id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

}
