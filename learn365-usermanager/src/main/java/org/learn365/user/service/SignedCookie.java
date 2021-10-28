package org.learn365.user.service;

import org.learn365.modal.user.SignedCookieResponse;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;

public interface SignedCookie {

    SignedCookieResponse generateSignedCookie() throws InvalidKeySpecException, IOException;
}
