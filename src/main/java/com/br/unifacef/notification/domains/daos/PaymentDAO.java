package com.br.unifacef.notification.domains.daos;

import com.br.unifacef.notification.domains.entities.Payment;
import com.br.unifacef.notification.domains.entities.User;
import com.br.unifacef.notification.domains.repositories.postgres.PaymentRepository;
import com.br.unifacef.notification.domains.repositories.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentRepository paymentRepository;

    public Optional<Payment> findById(Integer scheduleId){
        return paymentRepository.findByScheduleId(scheduleId);
    }
}
