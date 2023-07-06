package com.david.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}

@RestController
class HttpController {

	@GetMapping("/public")
	String publicRoute() {
		return "<h1>Public route, feel free to look around!</h1>";
	}

	@GetMapping("/private")
	String privateRoute(@AuthenticationPrincipal OidcUser oidcUser) {
		return String.format("""
				<h1>Private route, only authorize personal!</h1>
				<h3>Principal: %s</h3>
				<h3>Email attribute: %s</h3>
				<h3>Authorities: %s</h3>
				<h3>JWT: %s</h3>
				""", oidcUser, oidcUser.getAttribute("email"), oidcUser.getAuthorities(),
				oidcUser.getIdToken().getTokenValue());
	}
}
