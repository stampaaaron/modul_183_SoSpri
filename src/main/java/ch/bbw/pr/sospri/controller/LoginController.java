package ch.bbw.pr.sospri.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class LoginController {

  @RequestMapping(path = "/login")
  public String login(Model model) {
    return "login";
  }

  @RequestMapping(path = "/logout")
  public String logout(Model model) {
    return "logout";
  }

}
