package com.example.stockmarketsim.config;

import com.example.stockmarketsim.interceptors.UserIdentificationInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class RestControllerInterceptor implements WebMvcConfigurer {
  private final ApplicationContext applicationContext;

  public RestControllerInterceptor(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new UserIdentificationInterceptor(applicationContext))
        .addPathPatterns("/**");
  }
}
