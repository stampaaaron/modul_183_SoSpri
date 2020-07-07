package ch.bbw.pr.sospri.controller.helper;

import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.security.Role;
import ch.bbw.pr.sospri.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;

@AllArgsConstructor
@Component
public class RequestHelper {

  public final UserService userService;

  public String renderPage(String page, Model model, Principal principal) {
    User user = userService.getByUserName(principal.getName());
    if (user != null) {
      if (user.getRole() == Role.SUPERVISOR) {
        model.addAttribute("isSupervisor", true);
      } else if (user.getRole() == Role.ADMIN) {
        model.addAttribute("isAdmin", true);
      }
    }
    return page;
  }

}
