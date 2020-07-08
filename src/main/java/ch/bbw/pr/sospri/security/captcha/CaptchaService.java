package ch.bbw.pr.sospri.security.captcha;

import ch.bbw.pr.sospri.config.CaptchaSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class CaptchaService {

  private final CaptchaSettings captchaSettings;

  private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

  public boolean validateCaptcha(String captchaResponse){

    if (StringUtils.isEmpty(captchaResponse)) {
      return false;
    }

    RestTemplate restTemplate = new RestTemplate();

    MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
    requestMap.add("secret", captchaSettings.getSecret());
    requestMap.add("response", captchaResponse);

    CaptchaResponse apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, CaptchaResponse.class);
    if(apiResponse == null){
      return false;
    }

    return Boolean.TRUE.equals(apiResponse.getSuccess());
  }
}
