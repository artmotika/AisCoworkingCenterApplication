package com.aiscoworking.aiscoworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name="User_group")
public class AisUserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public AisUserGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AisUserGroup(String name) {
        this.name = name;
    }

    protected AisUserGroup() {}

    @JsonIgnore
    @ManyToMany(mappedBy = "aisUserGroups", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<AisUser> aisUsers = new ArrayList<>();
}