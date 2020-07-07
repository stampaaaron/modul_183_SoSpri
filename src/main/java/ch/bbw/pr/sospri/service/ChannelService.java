package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.domain.Channel;
import ch.bbw.pr.sospri.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChannelService {

  private final ChannelRepository channelRepository;

  public List<Channel> getAllChannels() {
    return channelRepository.findAll();
  }

  public Channel getChannelById(Long id) {
    return channelRepository.findById(id).orElse(null);
  }

  public Long addChannel(Channel channel) {
    Channel newChannel = channelRepository.save(channel);
    return newChannel.getId();
  }

}
