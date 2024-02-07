package org.example.models;

public class BCryptPasswordEncoder {
    public static String encode(String password) {
        return password;
    }

    public boolean matches(String password, String pwHash) {
        return false;
    }

}
