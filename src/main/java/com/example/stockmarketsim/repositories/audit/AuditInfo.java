package com.example.stockmarketsim.repositories.audit;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuditInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "CRTTS")
  private Timestamp createdTimestamp;

  @Column(name = "CRTUSRID", length = 30)
  private Integer createdUserId;

  @Column(name = "ROWCHGTS", updatable = false)
  private Timestamp rowChangedTimestamp;

  @Column(name = "UPDTUSRID", length = 30)
  private Integer updatedUserId;

  public Timestamp getCreatedTimestamp() {
    return this.createdTimestamp;
  }

  public void setCreatedTimestamp(final Timestamp createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public Integer getCreatedUserId() {
    return this.createdUserId;
  }

  public void setCreatedUserId(final Integer createdUserId) {
    this.createdUserId = createdUserId;
  }

  public Timestamp getRowChangedTimestamp() {
    return this.rowChangedTimestamp;
  }

  public void setRowChangedTimestamp(final Timestamp rowChangedTimestamp) {
    this.rowChangedTimestamp = rowChangedTimestamp;
  }

  public Integer getUpdatedUserId() {
    return this.updatedUserId;
  }

  public void setUpdatedUserId(final Integer updatedUserId) {
    this.updatedUserId = updatedUserId;
  }
}
