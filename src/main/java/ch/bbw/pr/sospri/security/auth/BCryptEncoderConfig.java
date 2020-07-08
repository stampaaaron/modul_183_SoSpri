package ch.bbw.pr.sospri.security.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class BCryptEncoderConfig {

  private static final int STRENGTH = 10;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
  }

}
