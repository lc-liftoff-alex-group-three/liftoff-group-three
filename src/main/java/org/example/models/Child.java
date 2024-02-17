package org.example.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Child extends AbstractEntity{

    @NotBlank(message = "Child's first name is required")
    @Size(max = 50, message = "Child's name is too long")
    private String firstName;

    @NotBlank (message = "Child's last name is required")
    @Size (max = 50, message = "Child's last name is too long")
    private String lastName;

    private int points;

    @ManyToOne
    private Parent parent;

    @OneToOne(cascade = CascadeType.ALL)
    private ChildUser userAccount;


    public Child() {
    }


    public Child(String firstName, String lastName, Parent parent, ChildUser newChildUser) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.parent = parent;
        this.userAccount = newChildUser;
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

    public ChildUser getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(ChildUser userAccount) {
        this.userAccount = userAccount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }




}