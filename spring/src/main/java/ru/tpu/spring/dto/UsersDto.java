package ru.tpu.spring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.PrimitiveIterator;

@Data
@Builder
public class UsersDto {
    private Integer id;
    private String name;
    private String login;
    private String email;
}
