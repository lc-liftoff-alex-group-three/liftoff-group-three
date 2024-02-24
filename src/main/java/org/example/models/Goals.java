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
    private boolean completed; // New field for tracking completion status

    private LocalDate completedDate; // New field for tracking completion date

    private boolean approved; // New field for tracking approval status

    private LocalDate approvedDate; // New field for tracking approval date


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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }
}
