package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.AisUser;

import java.util.List;

public interface AisUserService {
    AisUser saveAisUser(AisUser aisUser);
    List<AisUser> getAllAisUsers();
    AisUser getAisUserById(long id);
    AisUser updateAisUser(AisUser aisUser, long id);
    AisUser updateAisUserGroup(long aisUserId, long aisUserGroupId);
    void deleteAisUser(long id);
}
