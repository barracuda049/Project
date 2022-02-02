package ru.tpu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tpu.spring.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByLogin(String login);
}
