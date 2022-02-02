package ru.tpu.spring.dto;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import ru.tpu.spring.entity.Users;

@Component
public class UsersConverter {

    public Users fromUserDtoToUser(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setEmail(usersDto.getEmail());
        users.setName(usersDto.getName());
        users.setLogin(usersDto.getLogin());
        return users;
    }

    public UsersDto fromUserToUserDto(Users users){
        return UsersDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .login(users.getLogin())
                .name(users.getName())
                .build();
    }

}

