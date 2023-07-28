package com.example.stockmarketsim.controllers;

import static org.h2.engine.Constants.DEFAULT_PAGE_SIZE;

import com.example.stockmarketsim.models.responses.UserResponseDTO;
import com.example.stockmarketsim.services.UserService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> createUser() {
    return userService.createOrFindUser();
  }

  @GetMapping
  public PageImpl<UserResponseDTO> getAllUsers(
      @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
    return userService.getAllUsers(pageable);
  }

  @DeleteMapping
  public ResponseEntity<String> deleteUser(@PathVariable(name = "email") String email) {
    return userService.deleteByEmail(email);
  }
}
