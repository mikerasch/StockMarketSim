package com.example.stockmarketsim.repositories.audit;

public interface Auditable {
  AuditInfo getAuditInfo();

  void setAuditInfo(AuditInfo auditInfo);
}
