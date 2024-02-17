package org.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Entity
public class Goals extends AbstractEntity {
    @NotBlank
    private String goalName;
    @NotBlank
    private String description;
    @ManyToOne // Many goals can be associated with one reward
    private Rewards reward;
    @ManyToOne // Many goals are assigned to one child user
    private ChildUser childAssigned;
    private LocalDate dueDate;

    @Column(nullable = false)
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

    public ChildUser getChildAssigned() {
        return childAssigned;
    }
    public Rewards getReward() {
        return reward;
    }

    public void setReward(Rewards reward) {
        this.reward = reward;
    }


    public void setChildAssigned(ChildUser childAssigned) {
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
