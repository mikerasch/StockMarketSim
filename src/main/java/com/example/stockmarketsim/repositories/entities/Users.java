package com.example.stockmarketsim.repositories.entities;

import com.example.stockmarketsim.repositories.audit.AuditInfo;
import com.example.stockmarketsim.repositories.audit.AuditListener;
import com.example.stockmarketsim.repositories.audit.Auditable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@EntityListeners(AuditListener.class)
public class Users implements Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private Integer personId;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private BigDecimal balance;

  @OneToMany(cascade = CascadeType.ALL)
  private List<StocksOwned> stocksOwned;

  @OneToMany(cascade = CascadeType.ALL)
  private List<StockTransactions> stockTransactions;

  @Embedded private AuditInfo auditInfo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public List<StocksOwned> getStocksOwned() {
    return stocksOwned;
  }

  public void setStocksOwned(List<StocksOwned> stocksOwned) {
    this.stocksOwned = stocksOwned;
  }

  @Override
  public AuditInfo getAuditInfo() {
    return auditInfo;
  }

  @Override
  public void setAuditInfo(AuditInfo auditInfo) {
    this.auditInfo = auditInfo;
  }

  public List<StockTransactions> getStockTransactions() {
    return stockTransactions;
  }

  public void setStockTransactions(List<StockTransactions> stockTransactions) {
    this.stockTransactions = stockTransactions;
  }
}
