package com.zuehlke.securesoftwaredevelopment.config;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@WebListener
public class CsrfHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String token = createToken();
        se.getSession().setAttribute("CSRF_TOKEN", token);
    }

    private static String createToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[16];
        secureRandom.nextBytes(token);
        byte[] base64token = Base64.encodeBase64(token);
        return new String(base64token, StandardCharsets.UTF_8);
    }
}
