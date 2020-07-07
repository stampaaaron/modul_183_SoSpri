package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.converter.UserConverter;
import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.controller.message.RegisterMember;
import ch.bbw.pr.sospri.repository.UserRepository;
import ch.bbw.pr.sospri.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * MemberService
 *
 * @author Peter Rutschmann
 * @version 09.04.2020
 * <p>
 * https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
 * https://reflectoring.io/spring-security-password-handli
 */
@AllArgsConstructor
@Service
@Transactional
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public Iterable<User> getAll() {
    return repository.findAll();
  }

  public User add(RegisterMember registerMember) {
    User user = UserConverter.convert(registerMember);
    user.setRole(Role.USER);
    user.setUsername(generateUserName(registerMember.getPrename(), registerMember.getLastname()));

    String encodedPassword = passwordEncoder.encode(registerMember.getPassword());

    user.setPassword(encodedPassword);
    return repository.save(user);
  }

  private String generateUserName(String firstName, String lastName) {
    StringBuilder userName = new StringBuilder(firstName.toLowerCase()).append(".").append(lastName.toLowerCase());
    while (getByUserName(userName.toString()) != null) {
      userName.append(1);
    }
    return userName.toString();
  }

  public void update(User user) {
    User updateUser = getById(user.getId());
    updateUser.setPrename(user.getPrename());
    updateUser.setLastname(user.getLastname());
    updateUser.setRole(user.getRole());
    repository.save(updateUser);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public User getById(Long id) {
    return repository.findById(id).orElseGet(() -> {
      System.out.println("MemberService:getById(), id does not exist in repository: " + id);
      return null;
    });
  }

  public User getByUserName(String username) {
    User user = repository.findByUsername(username);
    if (username == null) {
      System.out.println("MemberService:getByUserName(), username does not exist in repository: " + username);
    }
    return user;
  }
}
