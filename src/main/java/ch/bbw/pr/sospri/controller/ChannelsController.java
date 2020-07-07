package ch.bbw.pr.sospri.controller;

import ch.bbw.pr.sospri.controller.helper.RequestHelper;
import ch.bbw.pr.sospri.controller.message.MessageDto;
import ch.bbw.pr.sospri.domain.Channel;
import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.service.ChannelService;
import ch.bbw.pr.sospri.service.MessageService;
import ch.bbw.pr.sospri.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

/**
 * ChannelsController
 *
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(path = "/channels")
public class ChannelsController {
  private final MessageService messageService;
  private final UserService userService;
  private final ChannelService channelService;

  private final RequestHelper requestHelper;

  @GetMapping
  public String getRequestChannel(Model model, Principal principal) {

    if (model.getAttribute("selectedChannel") == null) {
    	return getChannelDetails(1L, model, principal);
		}

    model.addAttribute("channels", channelService.getAllChannels());

    return requestHelper.renderPage("channel", model, principal);
  }

  @GetMapping("/{id}")
	public String getChannelDetails(@PathVariable("id") Long id, Model model, Principal principal) {
  	model.addAttribute("selectedChannel", channelService.getChannelById(id));
    model.addAttribute("message", new MessageDto(null, "", id));
    return getRequestChannel(model, principal);
	}

  @PostMapping("/message")
  public String postRequestChannel(Model model,
                                         @ModelAttribute @Valid MessageDto message,
                                         BindingResult bindingResult,
                                         Principal principal) {

    if (bindingResult.hasErrors()) {
      return getRequestChannel(model, principal);
    }
    User user = userService.getByUserName(principal.getName());
    messageService.add(message.getContent(), message.getChannelId(), user);
    log.info("user with id = {} is added new message", user.getId());

    return getChannelDetails(message.getChannelId(), model, principal);
  }

  @GetMapping("/message/delete")
  public String deleteMessage(@RequestParam Long id, Model model, Principal principal) {
     messageService.deleteById(id);
     log.info("deleted message with id = {}", id);
    return getRequestChannel(model, principal);
  }

  @PostMapping
	public String addChannel(@ModelAttribute Channel channel, Model model, Principal principal) {
  	long channelId = channelService.addChannel(channel);
  	log.info("channel with id = {} has been added", channelId);
  	return getChannelDetails(channelId, model, principal);
	}

  @GetMapping(path = "/add")
	public String getChannelForm(Model model) {
  	model.addAttribute("channel", new Channel());
  	return "channel-form";
	}

}
