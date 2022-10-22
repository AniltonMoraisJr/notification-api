package com.br.unifacef.notification.domains.repositories.postgres;

import com.br.unifacef.notification.domains.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
