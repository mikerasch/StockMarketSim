package com.example.stockmarketsim.utils;

import com.example.stockmarketsim.config.ApplicationContextImpl;
import com.example.stockmarketsim.repositories.audit.UserIdentificationHolder;

public class AppUtility {
  private AppUtility() {
    throw new UnsupportedOperationException("Dumb Dumb this is private");
  }

  public static int getIdentificationHolder() {
    return ApplicationContextImpl.getApplicationContext()
        .getBean(UserIdentificationHolder.class)
        .getUserIdentification();
  }
}
