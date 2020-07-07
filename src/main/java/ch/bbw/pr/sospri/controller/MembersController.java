package ch.bbw.pr.sospri.controller;

import ch.bbw.pr.sospri.controller.helper.RequestHelper;
import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * UsersController
 *
 * @author Peter Rutschmann
 * @version 28.04.2020
 */
@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(path = "members")
public class MembersController {
  private final UserService userService;
  private final RequestHelper requestHelper;

  @GetMapping
  public String getRequestMembers(Model model, Principal principal) {
    model.addAttribute("members", userService.getAll());
    return requestHelper.renderPage("members", model, principal);
  }

  @GetMapping("/{id}")
  public String editMember(@PathVariable("id") long id, Model model) {
    User user = userService.getById(id);
    model.addAttribute("user", user);
    return "editmember";
  }

  @PostMapping
  public String editMember(User user, Model model, Principal principal) {
    userService.update(user);
    log.info("updated user with id = {}", user.getId());
    return getRequestMembers(model, principal);
  }

  @GetMapping("/delete/{id}")
  public String deleteMember(@PathVariable("id") long id, Model model, Principal principal) {
    userService.deleteById(id);
    log.info("deleted user with id = {}", id);
    return getRequestMembers(model, principal);
  }
}
