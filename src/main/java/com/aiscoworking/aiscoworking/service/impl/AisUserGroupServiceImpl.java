package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.AisUserGroup;
import com.aiscoworking.aiscoworking.repository.AisUserGroupRepository;
import com.aiscoworking.aiscoworking.service.AisUserGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AisUserGroupServiceImpl implements AisUserGroupService {

    private final AisUserGroupRepository aisUserGroupRepository;

    public AisUserGroupServiceImpl(AisUserGroupRepository aisUserGroupRepository) {
        super();
        this.aisUserGroupRepository = aisUserGroupRepository;
    }

    @Override
    public AisUserGroup saveAisUserGroup(AisUserGroup aisUserGroup) {
        return aisUserGroupRepository.save(aisUserGroup);
    }

    @Override
    public List<AisUserGroup> getAllAisUserGroups() {
        return aisUserGroupRepository.findAll();
    }

    @Override
    public AisUserGroup getAisUserGroupById(long id) {
        return aisUserGroupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AisUserGroup", "Id", id));
    }

    @Override
    public AisUserGroup updateAisUserGroup(AisUserGroup aisUserGroup, long id) {
        // check whether aisUserGroup with given id is exist in DB or not
        AisUserGroup existingAisUserGroup = aisUserGroupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AisUserGroup", "Id", id));
        existingAisUserGroup.setName(aisUserGroup.getName());
        // save exsiting aisUserGroup
        aisUserGroupRepository.save(existingAisUserGroup);
        return existingAisUserGroup;
    }

    @Override
    public void deleteAisUserGroup(long id) {
        // check whether aisUserGroup with given id is exist in DB or not
        aisUserGroupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AisUserGroup", "Id", id));
        aisUserGroupRepository.deleteById(id);
    }
}
