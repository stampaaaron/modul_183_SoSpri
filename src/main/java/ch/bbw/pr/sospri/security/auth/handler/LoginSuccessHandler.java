package ch.bbw.pr.sospri.security.auth.handler;


import ch.bbw.pr.sospri.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Service
public class LoginSuccessHandler extends
    SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication)
      throws ServletException, IOException {

    Object user = authentication.getPrincipal();
    if (user instanceof User) {
      User domainUser = (User) user;
      log.info("user with id = {} has been logged in successfully", domainUser.getId());
    } else {
      log.info(user);
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }

}
