package ru.tpu.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tpu.spring.dto.UsersConverter;
import ru.tpu.spring.dto.UsersDto;
import ru.tpu.spring.entity.Users;
import ru.tpu.spring.exception.ValidationException;
import ru.tpu.spring.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultUsersService implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;

    @Override
    public UsersDto saveUser(UsersDto usersDto) throws ValidationException {
        validateUserDto(usersDto);
        Users savedUser = usersRepository.save(usersConverter.fromUserDtoToUser(usersDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }

    private void validateUserDto(UsersDto usersDto) throws ValidationException {
        if(isNull(usersDto)){
            throw new ValidationException("Object user is null");
        }
        if(isNull(usersDto.getLogin()) || usersDto.getLogin().isEmpty()){
            throw new ValidationException("Login is empty");
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public UsersDto findByLogin(String login) {
        return usersConverter.fromUserToUserDto(usersRepository.findByLogin(login));
    }

    @Override
    public List<UsersDto> findAll() {
       return usersRepository.findAll()
               .stream()
               .map(usersConverter::fromUserToUserDto)
               .collect(Collectors.toList());
    }
}
