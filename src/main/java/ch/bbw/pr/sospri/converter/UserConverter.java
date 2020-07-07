package ch.bbw.pr.sospri.converter;

import ch.bbw.pr.sospri.domain.User;
import ch.bbw.pr.sospri.controller.message.RegisterMember;

public final class UserConverter {

  private UserConverter() {}

  public static User convert(RegisterMember registerMember) {
    return User.builder()
        .prename(registerMember.getPrename())
        .lastname(registerMember.getLastname()).build();
  }

}
