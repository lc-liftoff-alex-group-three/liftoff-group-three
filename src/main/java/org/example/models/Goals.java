package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Goals {
    @Id
    private String goalName;
    private String description;
    private String childAssigned;
    private LocalDate dueDate;

    private String dragonReward;

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChildAssigned() {
        return childAssigned;
    }

    public void setChildAssigned(String childAssigned) {
        this.childAssigned = childAssigned;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDragonReward() {
        return dragonReward;
    }

    public void setDragonReward(String dragonReward) {
        this.dragonReward = dragonReward;
    }
}
