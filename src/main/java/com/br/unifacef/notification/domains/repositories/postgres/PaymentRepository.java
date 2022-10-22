package com.br.unifacef.notification.domains.repositories.postgres;

import com.br.unifacef.notification.domains.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByScheduleId(Integer scheduleId);
}
