package com.br.unifacef.notification.domains.repositories.postgres;

import com.br.unifacef.notification.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
