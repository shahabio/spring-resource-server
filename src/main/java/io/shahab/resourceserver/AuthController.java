package io.shahab.resourceserver;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final RestTemplate restTemplate;
  private final OAuth2RestTemplate oAuth2RestTemplate;

  public AuthController(@Qualifier("restTemplate") RestTemplate restTemplate, OAuth2RestTemplate oAuth2RestTemplate) {
    this.restTemplate = restTemplate;
    this.oAuth2RestTemplate = oAuth2RestTemplate;
  }

  @PostMapping("/login")
  public String login() {
    String principal = restTemplate.postForObject("http://localhost:9090/oauth/token?username=shahab&password=123&scope=write&grant_type=password", null, String.class);
    System.out.println(principal);
    return "Logged In. " + principal;
  }

  @GetMapping("/me")
  public String me() {
    String principal = oAuth2RestTemplate.getForObject("http://localhost:9090/me", String.class);
    System.out.println(principal);
    return "Logged In. " + principal;
  }
}
