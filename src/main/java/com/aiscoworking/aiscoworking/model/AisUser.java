package com.aiscoworking.aiscoworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name="User")
public class AisUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bonus_points", nullable = false)
    private int bonusPoints;

    public AisUser(long id, String username, String firstName, String password, int bonusPoints) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.password = password;
        this.bonusPoints = bonusPoints;
    }

    public AisUser(String username, String firstName, String password, int bonusPoints) {
        this.username = username;
        this.firstName = firstName;
        this.password = password;
        this.bonusPoints = bonusPoints;
    }

    protected AisUser() {}

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Users_have_groups",
            joinColumns = { @JoinColumn(name = "ais_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ais_user_group_id") }
    )
    @JsonIgnore
    private Collection<AisUserGroup> aisUserGroups = new ArrayList<>();
}
