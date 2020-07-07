package ch.bbw.pr.sospri.controller;

import ch.bbw.pr.sospri.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.service.UserService;
import ch.bbw.pr.sospri.controller.message.RegisterMember;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * RegisterController
 *
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Log4j2
@Controller
@RequestMapping(path = "register")
public class RegisterController {

	private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping()
  public String getRequestRegistMembers(Model model) {
    model.addAttribute("registerMember", new RegisterMember());
    return "register";
  }

  @PostMapping()
  public String postRequestRegistMembers(@Valid @ModelAttribute RegisterMember registerMember, BindingResult bindingResult, Model model) {
    if (!registerMember.getPassword().equals(registerMember.getPasswordConfirmation())) {
      bindingResult.rejectValue("passwordConfirmation", "", "Passwords don't match");
    }
    if (bindingResult.hasErrors()) {
      model.addAttribute("registerMember", registerMember);
      return "register";
    }

		User user = userService.add(registerMember);
    model.addAttribute("user", user);

    return "registerconfirmed";
  }
}
