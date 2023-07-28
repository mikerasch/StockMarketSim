package com.example.stockmarketsim.repositories;

import com.example.stockmarketsim.repositories.entities.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
  boolean existsByEmail(String email);

  void deleteByEmailIgnoreCase(String email);

  Optional<Users> findByEmail(String email);

  Optional<Users> findByPersonId(Integer personId);

  Optional<Users> findByPersonIdAndStocksOwnedTicker(Integer personId, String ticker);
}
