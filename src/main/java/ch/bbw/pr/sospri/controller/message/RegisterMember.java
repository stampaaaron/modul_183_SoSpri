package ch.bbw.pr.sospri.controller.message;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * To regist a new Member
 *
 * @author peter.rutschmann
 * @version 27.04.2020
 */
@Data
@ToString
public class RegisterMember {

  @NotEmpty(message = "Firstname shouldn't be empty")
  @Size(min = 2, max = 25, message = "Length of firstname should be between 2 and 25 characters")
  private String prename;
  @NotEmpty(message = "Lastname shouldn't be empty")
  @Size(min = 2, max = 25, message = "Length of lastname should be between 2 and 25 characters")
  private String lastname;
  @NotEmpty(message = "Password shouldn't be empty")
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&+=]{8,}$",
      message = "Password doesn't match the given criteria."
  )
  private String password;
  @NotEmpty(message = "Password shouldn't be empty")
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&+=]{8,}$",
      message = "Password doesn't match the given criteria."
  )
  private String passwordConfirmation;
}
