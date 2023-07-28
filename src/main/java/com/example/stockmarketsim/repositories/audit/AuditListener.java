package com.example.stockmarketsim.repositories.audit;

import static com.example.stockmarketsim.utils.AppUtility.getIdentificationHolder;
import static java.util.Objects.isNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {
  /** automatic property set before any database update */
  @PreUpdate
  public void setUpdatedAuditFields(Auditable auditable) {
    AuditInfo auditInfo = auditable.getAuditInfo();
    if (isNull(auditInfo)) {
      auditInfo = new AuditInfo();
    }
    if (isNull(auditInfo.getUpdatedUserId())) {
      auditInfo.setUpdatedUserId(getIdentificationHolder());
    }
    auditable.setAuditInfo(auditInfo);
  }
  /** automatic property set before any database insert */
  @PrePersist
  public void setCreatedAuditFields(final Auditable auditable) {
    AuditInfo auditInfo = auditable.getAuditInfo();
    if (isNull(auditInfo)) {
      auditInfo = new AuditInfo();
    }

    // update only if null
    // if createdUserId or updatedUserId is already set in code, don't overwrite.
    if (isNull(auditInfo.getCreatedUserId())) {
      auditInfo.setCreatedUserId(getIdentificationHolder());
    }
    if (isNull(auditInfo.getUpdatedUserId())) {
      auditInfo.setUpdatedUserId(getIdentificationHolder());
    }

    // set created timestamp
    if (isNull(auditInfo.getCreatedTimestamp()))
      auditInfo.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));

    // set row changed timestamp
    if (isNull(auditInfo.getRowChangedTimestamp()))
      auditInfo.setRowChangedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    auditable.setAuditInfo(auditInfo);
  }
}
