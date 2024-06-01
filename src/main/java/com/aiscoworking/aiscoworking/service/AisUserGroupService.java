package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.AisUserGroup;

import java.util.List;

public interface AisUserGroupService {
    AisUserGroup saveAisUserGroup(AisUserGroup aisUserGroup);
    List<AisUserGroup> getAllAisUserGroups();
    AisUserGroup getAisUserGroupById(long id);
    AisUserGroup updateAisUserGroup(AisUserGroup aisUserGroup, long id);
    void deleteAisUserGroup(long id);
}
