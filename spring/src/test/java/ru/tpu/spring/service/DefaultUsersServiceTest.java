package ru.tpu.spring.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tpu.spring.dto.UsersConverter;
import ru.tpu.spring.dto.UsersDto;
import ru.tpu.spring.entity.Users;
import ru.tpu.spring.exception.ValidationException;
import ru.tpu.spring.repository.UsersRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DefaultUsersServiceTest {

    private UsersService usersService;
    private UsersRepository usersRepository;
    private UsersConverter usersConverter;
    private Users users;

    @BeforeEach
    void setUp(){
        usersRepository = mock(UsersRepository.class);
        usersConverter = new UsersConverter();
        users = new Users();
        users.setName("testName");
        users.setLogin("testLogin");
        users.setId(1);
        when(usersRepository.save(any())).thenReturn(users);
        usersService = new DefaultUsersService(usersRepository, usersConverter);
    }

    @Test
    void saveUserReturnUserDto() throws ValidationException {
        UsersDto usersDto = UsersDto.builder().login("testLogin").build();
        UsersDto savedUsersDto = usersService.saveUser(usersDto);
        assertThat(savedUsersDto).isNotNull();
        assertThat(savedUsersDto.getLogin()).isEqualTo("testLogin");
    }

    @Test
    void saveUserWithNullLoginThrowsValidationException(){
        UsersDto usersDto = UsersDto.builder().build();
        assertThrows(ValidationException.class, () -> usersService.saveUser(usersDto), "Login is empty");
    }
}