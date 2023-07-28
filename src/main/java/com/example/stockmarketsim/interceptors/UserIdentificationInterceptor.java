package com.example.stockmarketsim.interceptors;

import com.example.stockmarketsim.repositories.audit.UserIdentificationHolder;
import com.uline.security.model.UserContext;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserIdentificationInterceptor implements HandlerInterceptor {
  private final ApplicationContext applicationContext;

  public UserIdentificationInterceptor(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public boolean preHandle(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof UserContext) {
      UserContext userContext = (UserContext) authentication.getPrincipal();
      if (Objects.nonNull(userContext)
          && Objects.nonNull(userContext.getUlineUser())
          && (Objects.nonNull(applicationContext))) {
        UserIdentificationHolder userIdentificationHolder =
            applicationContext.getBean(UserIdentificationHolder.class);
        userIdentificationHolder.setUserIdentification(userContext.getUlineUser().getPersonId());
      }
    }
    return true;
  }
}
