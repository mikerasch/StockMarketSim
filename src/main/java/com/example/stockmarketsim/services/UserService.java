package com.example.stockmarketsim.services;

import com.example.stockmarketsim.converters.UserDtoConverter;
import com.example.stockmarketsim.models.responses.UserResponseDTO;
import com.example.stockmarketsim.repositories.UserRepository;
import com.example.stockmarketsim.repositories.entities.Users;
import com.uline.common.exception.BadRequestException;
import com.uline.security.model.UlineUser;
import com.uline.security.service.UserResolver;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final UserDtoConverter userDtoConverter;
  private final UserResolver userResolver;

  public UserService(
      UserRepository userRepository, UserDtoConverter userDtoConverter, UserResolver userResolver) {
    this.userRepository = userRepository;
    this.userDtoConverter = userDtoConverter;
    this.userResolver = userResolver;
  }

  public ResponseEntity<UserResponseDTO> createOrFindUser() {
    // this will work as user is already an uline employee if they got this far
    UlineUser ulineUser = userResolver.getCurrentUser().orElseThrow(BadRequestException::new);
    Optional<Users> alreadyAUser = userRepository.findByEmail(ulineUser.getEmail());
    if (alreadyAUser.isPresent()) {
      return new ResponseEntity<>(userDtoConverter.convert(alreadyAUser.get()), HttpStatus.OK);
    }
    String[] fullNameSplit = ulineUser.getFullName().split(" ");
    Users user = new Users();
    user.setEmail(ulineUser.getEmail());
    user.setFirstName(fullNameSplit[0]);
    user.setLastName(fullNameSplit[1]);
    user.setPersonId(ulineUser.getPersonId());
    user.setBalance(BigDecimal.valueOf(100_000));
    userRepository.save(user);
    return new ResponseEntity<>(userDtoConverter.convert(user), HttpStatus.CREATED);
  }

  public PageImpl<UserResponseDTO> getAllUsers(Pageable pageable) {
    Page<Users> page = userRepository.findAll(pageable);
    return new PageImpl<>(
        page.getContent().stream().map(userDtoConverter::convert).collect(Collectors.toList()),
        pageable,
        page.getContent().size());
  }

  public ResponseEntity<String> deleteByEmail(String email) {
    userRepository.deleteByEmailIgnoreCase(email);
    return ResponseEntity.ok("Deleted");
  }
}
