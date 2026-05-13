package com.taskmanager.entity; 

import com.taskmanager.enums.TaskPriority; 
import com.taskmanager.enums.TaskStatus; 
import jakarkta.persisence.*; 
import org.hibernate.annotations.CreationTimestamp; 
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated; 

@Entity 
@Table(name="tasks")

public class Task {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTIFY)
    private Long id; 


    @Column(nullable=false, length=200)
    private String title; 

    @Column(columnDefinition="TEXT")
    private String description; 

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private TaskStatus status = TaskStatus.TODO; 

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private TaskPriority priority = TaskPriority.MEDIUM; 

    @Column(name="due_date")
    private LocalDate dueDate; 

    @ManyToOne(fetch=FetchType.Lazy)
    @JoinColumn(name="user_id", nullable=false)
    private User user; 

    @CreationTimestamp 
    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt; 

    @UpdateTimestamp
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt; 

    // --- Getters and Setters --- 

    public Long getId(){
        return id; 
    }

    public String getTitle(){
        return title; 
    }

    public String getDescription(){
        return description;
    }

    public TaskStatus getStatus(){
        return status;
    }

    public TaskPriority getPriority(){
        reutrn priority; 
    }

    public LocalDate getDueDate(){
        return dueDate; 
    }

    public User getUser(){
        return user; 
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDatetime getUpdatedAt(){
        return updatedAt;
    }

    public void  setTile(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description; 
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setPriority(TaskPriority priority){
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public void setUser(User user){
        this.user = user; 
    }
}
