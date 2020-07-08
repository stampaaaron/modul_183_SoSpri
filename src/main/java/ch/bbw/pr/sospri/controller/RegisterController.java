package ch.bbw.pr.sospri.controller;

import ch.bbw.pr.sospri.controller.message.RegisterMember;
import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.security.captcha.CaptchaService;
import ch.bbw.pr.sospri.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * RegisterController
 *
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping(path = "register")
public class RegisterController {

	private final UserService userService;
	private final CaptchaService captchaService;

  @GetMapping()
  public String getRequestRegistMembers(Model model) {
    model.addAttribute("registerMember", new RegisterMember());
    return "register";
  }

  @PostMapping(produces = {"application/json"})
  public String postRequestRegistMembers(@Valid @ModelAttribute RegisterMember registerMember, BindingResult bindingResult, Model model, HttpServletRequest request) {
    if (!registerMember.getPassword().equals(registerMember.getPasswordConfirmation())) {
      bindingResult.rejectValue("passwordConfirmation", "", "Passwords don't match");
    }
    if (!captchaService.validateCaptcha(registerMember.getCaptchaResponse())) {
      bindingResult.rejectValue("captchaResponse", "", "Captcha not correct.");
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
