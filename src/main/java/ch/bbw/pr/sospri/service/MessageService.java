package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.domain.Channel;
import ch.bbw.pr.sospri.domain.Message;
import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * MessageService
 *
 * @author Peter Rutschmann
 * @version 25.06.2020
 */
@Service
@AllArgsConstructor
public class MessageService {

  private final MessageRepository messageRepository;
  private final ChannelService channelService;

  public void add(String message, Long channelId, User user) {
    Channel channel = channelService.getChannelById(channelId);

    Message newMessage = Message.builder()
        .content(message)
        .author(user.getPrename() + " " + user.getLastname())
        .origin(new Date())
        .channel(channel).build();

    messageRepository.save(newMessage);
  }

  public void update(Message message) {
    messageRepository.save(message);
  }

  public void deleteById(Long id) {
    messageRepository.deleteById(id);
  }
}
