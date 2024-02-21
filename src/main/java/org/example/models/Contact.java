package org.example.models;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

public class Contact {
    @NotEmpty(message= "User name is required.")
    @Size(min=4, max=50, message= "User name must be between 4 and 50 characters.")

    private String userName;
    @NotEmpty(message= "First Name is required.")
    private String firstName;
    @NotEmpty(message= "Last Name is required.")
    private String lastName;


    @Email(message= "Email should be valid.")
    private String email;
    @NotEmpty(message = "Message cannot be left blank.")
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
