package com.aiscoworking.aiscoworking.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SecurityAisUser implements UserDetails {

    private AisUser aisUser;

    public SecurityAisUser(AisUser aisUser) {
        this.aisUser = aisUser;
    }

    @Override
    public String getUsername() {
        return aisUser.getUsername();
    }

    @Override
    public String getPassword() {
        return aisUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] groupsIds = new String[aisUser.getAisUserGroups().size()];
        int idx = 0;
        for ( AisUserGroup group : aisUser.getAisUserGroups() ) {
            groupsIds[idx] = group.getName();
            idx++;
        }
        return Arrays.stream(groupsIds)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
