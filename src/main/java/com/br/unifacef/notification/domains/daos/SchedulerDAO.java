package com.br.unifacef.notification.domains.daos;

import com.br.unifacef.notification.domains.entities.Scheduler;
import com.br.unifacef.notification.domains.repositories.postgres.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchedulerDAO {
    private final SchedulerRepository schedulerRepository;

    public Optional<Scheduler> findById(Integer id){
        return schedulerRepository.findById(id);
    }
}
