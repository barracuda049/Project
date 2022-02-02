package ru.tpu.spring.service;

import ru.tpu.spring.dto.UsersDto;
import ru.tpu.spring.exception.ValidationException;

import java.util.List;

public interface UsersService {
    UsersDto saveUser(UsersDto usersDto) throws ValidationException;
    void deleteUser(Integer userId);
    UsersDto findByLogin(String login);

    List<UsersDto> findAll();
}
