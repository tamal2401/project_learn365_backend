package org.learn365.user.web;

import org.learn365.modal.user.SignedCookieResponse;
import org.learn365.user.service.SignedCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/v1/signedCookie/")
public class SignedCookieController {

    private SignedCookie signedCookie;

    public SignedCookieController(SignedCookie signedCookie) {
        this.signedCookie = signedCookie;
    }

    @GetMapping(value = {"generate-cookie", "service/generate-cookie"})
    public SignedCookieResponse generateCookie() throws InvalidKeySpecException, IOException {
        return signedCookie.generateSignedCookie();
    }
}
