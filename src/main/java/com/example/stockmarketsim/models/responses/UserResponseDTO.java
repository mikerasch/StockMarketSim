package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableUserResponseDTO.class)
public interface UserResponseDTO {
  String getFirstName();

  String getLastName();

  String getEmail();

  BigDecimal getBalance();
}
