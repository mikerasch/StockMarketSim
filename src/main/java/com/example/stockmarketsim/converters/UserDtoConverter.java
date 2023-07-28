package com.example.stockmarketsim.converters;

import com.example.stockmarketsim.models.responses.ImmutableUserResponseDTO;
import com.example.stockmarketsim.models.responses.UserResponseDTO;
import com.example.stockmarketsim.repositories.entities.Users;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<Users, UserResponseDTO> {

  @Override
  public UserResponseDTO convert(Users users) {
    return ImmutableUserResponseDTO.builder()
        .email(users.getEmail())
        .firstName(users.getFirstName())
        .lastName(users.getLastName())
        .balance(users.getBalance())
        .build();
  }
}
