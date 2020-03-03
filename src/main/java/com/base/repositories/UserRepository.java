package com.base.repositories;

import com.base.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmailAndEncryptedPassword(String email, String encryptedPasswod);

    Optional<User> findByEmailAndResetPasswordToken(String email, String s);

    Page<User> findAllByNameContainsOrCpfContainsOrEmailContains(String filter1, String filter2, String filter3, Pageable pageable);
}
