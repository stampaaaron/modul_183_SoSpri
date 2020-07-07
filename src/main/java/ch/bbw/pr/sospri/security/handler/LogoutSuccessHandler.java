package ch.bbw.pr.sospri.security.handler;

import ch.bbw.pr.sospri.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication)
      throws IOException, ServletException {

    User user = (User) authentication.getPrincipal();
    log.info("user with id = {} logged out successfully", user.getId());
    super.onLogoutSuccess(request, response, authentication);
  }


}
