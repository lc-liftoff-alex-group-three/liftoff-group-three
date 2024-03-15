package org.example.models;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmailVerification {

    private static final String EMAIL_VERIFICATION_API_URL = "https://emailverification.whoisxmlapi.com/api/v2";
    private static final String EMAIL_VERIFICATION_API_KEY = "at_375SRlyKcQG0QRzv5wFt88ubj1SwF";

    public boolean verifyEmail(String emailAddress) {
        String apiUrl = String.format("%s?apiKey=%s&emailAddress=%s", EMAIL_VERIFICATION_API_URL, EMAIL_VERIFICATION_API_KEY, emailAddress);
        RestTemplate restTemplate = new RestTemplate();
        EmailVerificationResponse response = restTemplate.getForObject(apiUrl, EmailVerificationResponse.class);

        if (response != null && response.isEmailValid()) {
            return true;
        } else {
            return false;
        }
    }
}