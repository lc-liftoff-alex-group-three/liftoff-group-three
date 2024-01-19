package org.example.models;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class User {

   @NotBlank(message = "Username is required!")
   @Size(min = 5, max = 15)
   private String username;

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    public User() {

    }

}
