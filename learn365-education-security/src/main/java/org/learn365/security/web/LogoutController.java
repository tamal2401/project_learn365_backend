package org.learn365.security.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user/")
public class LogoutController {

	private static final String BEARER_AUTHENTICATION = "Bearer ";
	private static final String HEADER_AUTHORIZATION = "authorization";

	private TokenStore tokenstore;

	public LogoutController(TokenStore tokenstore) {
		this.tokenstore = tokenstore;
	}

	@GetMapping(value = "logout")
	public void revokeAccess(HttpServletRequest request, HttpServletResponse response) {

		String token = request.getHeader(HEADER_AUTHORIZATION);

		if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {

			OAuth2AccessToken oAuth2AccessToken = tokenstore.readAccessToken(token.split(" ")[1]);

			if (oAuth2AccessToken != null) {
				tokenstore.removeAccessToken(oAuth2AccessToken);
			}

		}

		response.setStatus(HttpServletResponse.SC_OK);

	}
}
