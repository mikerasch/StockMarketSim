package com.example.stockmarketsim.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextImpl implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(@NonNull ApplicationContext applicationContext)
      throws BeansException {
    ApplicationContextImpl.syncSetAppContext(applicationContext);
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static synchronized void syncSetAppContext(ApplicationContext applicationContext) {
    ApplicationContextImpl.applicationContext = applicationContext;
  }
}
