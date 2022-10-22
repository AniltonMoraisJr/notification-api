package com.br.unifacef.notification.domains.daos;

import com.br.unifacef.notification.domains.entities.User;
import com.br.unifacef.notification.domains.repositories.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDAO {
    private final UserRepository userRepository;

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
}
