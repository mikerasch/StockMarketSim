package com.example.stockmarketsim.config;

import com.example.stockmarketsim.repositories.audit.UserIdentificationHolder;
import com.uline.security.jwt.JwtTokenInterceptor;
import com.uline.security.service.UserResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({"com.example.stockmarketsim", "com.uline.security"})
public class AppConfig {

  @Bean
  @Scope("request")
  public UserIdentificationHolder userIdentificationHolder() {
    return new UserIdentificationHolder();
  }

  @Bean
  public ApplicationContext applicationContext() {
    return ApplicationContextImpl.getApplicationContext();
  }

  @Bean
  public ClientHttpRequestInterceptor tokenInterceptor(
      final RestTemplate restTemplate, final UserResolver userResolver) {
    JwtTokenInterceptor interceptor = new JwtTokenInterceptor(userResolver);
    restTemplate.getInterceptors().add(interceptor);
    return interceptor;
  }
}
