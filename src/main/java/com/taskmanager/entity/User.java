package com.taskmanager.entity; 

import com.taskmanager.enums.Role; 
import jakarta.persistence.*; 
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.annotation.processing.Generated;


@Entity
@Table(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false, unique=true)
    private String email;

    @Column(name="password_hash", nullable=false)
    private String passwordHash; 

    @Column(name="full_name", nullable=false, length=100)
    private String fullName; 

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private Role role = Role.USER; 

    @CreationTimestamp 
    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt; 

    @UpdateTimestamp
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt; 

    // --- UserDetials contract --- 
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
    
    @Override
    public String getPassword(){
        return passwordHash;
    }

    @Override
    public String getUsername(){
        return email; 
    }

    @Override 
    public boolean isAccountNonExpired(){
        return true; 
    }

    @Override 
    public boolean isAccountNonLocked(){
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true; 
    }

    // -- Getters and Setters --- 
    public Long getId() {
        return id; 
    }

    public String getEmail() {
        return email; 
    }

    public String getFullName(){
        return fullName; 
    }

    public Role getRole(){
        return role; 
    }

    public LocalDateTime getCreatedAt(){
        return createdAt; 
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt; 
    }


    public void setEmail(String email){
        this.email = email; 
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public void setRole(Role role){
        this.role = role;
    }


}


