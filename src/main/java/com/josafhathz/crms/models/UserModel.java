package com.josafhathz.crms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 100 , nullable = false, unique = true)
    private String email;

    @Column(length = 150 , nullable = false , unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleModel> roleModels = new ArrayList<>();

    public Collection<RoleModel> getRoleModels() {
        return roleModels;
    }

    public void setRoleModels(Collection<RoleModel> roleModels) {
        this.roleModels = roleModels;
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UserModel(long id, String email, String username, String firstName, String lastName, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
