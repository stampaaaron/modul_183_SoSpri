package ch.bbw.pr.sospri.security.handler;

import ch.bbw.pr.sospri.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException exc) throws IOException, ServletException {

    Authentication auth
        = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) auth.getPrincipal();
    if (auth != null) {
      log.warn("user with id = {} tried to access page {}", user.getId(), request.getRequestURL());
    }

    response.sendRedirect(request.getContextPath() + "/403.html");
  }
}
