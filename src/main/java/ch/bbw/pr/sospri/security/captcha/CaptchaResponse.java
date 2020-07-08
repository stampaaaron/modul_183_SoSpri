package ch.bbw.pr.sospri.security.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CaptchaResponse {
  private Boolean success;
  private Date timestamp;
  private String hostname;
  @JsonProperty("error-codes")
  private List<String> errorCodes;
}
