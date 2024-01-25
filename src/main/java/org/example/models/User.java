package org.example.models;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



public class User {

   @NotBlank(message = "Username is required!")
   @Size(min = 5, max = 15)
   private String username;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;



    public User() {

    }

    public User(String username, String email, String password, String verifyPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
