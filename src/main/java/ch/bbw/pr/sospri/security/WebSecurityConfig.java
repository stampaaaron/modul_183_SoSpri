package ch.bbw.pr.sospri.security;

import ch.bbw.pr.sospri.security.handler.CustomAccessDeniedHandler;
import ch.bbw.pr.sospri.security.handler.LoginFailureHandler;
import ch.bbw.pr.sospri.security.handler.LoginSuccessHandler;
import ch.bbw.pr.sospri.security.handler.LogoutSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final LoginSuccessHandler loginSuccessHandler;
  private final LoginFailureHandler loginFailureHandler;
  private final LogoutSuccessHandler logoutSuccessHandler;
  private final CustomAccessDeniedHandler accessDeniedHandler;

  @Autowired
  public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/fragments/**").permitAll()
        .antMatchers("/img/**").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/members").hasAuthority(Role.ADMIN.name())
        .antMatchers("/channels/add").hasAnyAuthority(Role.ADMIN.name(), Role.SUPERVISOR.name())
        .anyRequest().authenticated()
        .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        .and().formLogin().loginPage("/login").permitAll()
        .and().oauth2Login().loginPage("/login")
        .failureHandler(loginFailureHandler)
        .successHandler(loginSuccessHandler)
        .and().logout()
        .logoutUrl("/logout")
        .permitAll().logoutSuccessHandler(logoutSuccessHandler);

    http.csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

}
