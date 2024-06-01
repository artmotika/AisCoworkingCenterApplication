package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceExistException;
import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.AisUser;
import com.aiscoworking.aiscoworking.model.AisUserGroup;
import com.aiscoworking.aiscoworking.repository.AisUserGroupRepository;
import com.aiscoworking.aiscoworking.repository.AisUserRepository;
import com.aiscoworking.aiscoworking.service.AisUserGroupService;
import com.aiscoworking.aiscoworking.service.AisUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AisUserServiceImpl implements AisUserService {

    private final AisUserRepository aisUserRepository;
    private final AisUserGroupService aisUserGroupService;
    private final PasswordEncoder encoder;

    public AisUserServiceImpl(AisUserRepository aisUserRepository, AisUserGroupService aisUserGroupService, PasswordEncoder encoder) {
        super();
        this.aisUserRepository = aisUserRepository;
        this.aisUserGroupService = aisUserGroupService;
        this.encoder = encoder;
    }

    @Override
    public AisUser saveAisUser(AisUser aisUser) {
        for ( AisUser user : aisUserRepository.findAll() ) {
            if (Objects.equals(user.getUsername(), aisUser.getUsername())) {
                throw new ResourceExistException("AisUser", "username", aisUser.getUsername());
            }
        }
        return aisUserRepository.save(aisUser);
    }

    @Override
    public List<AisUser> getAllAisUsers() {
        return aisUserRepository.findAll();
    }

    @Override
    public AisUser getAisUserById(long id) {
        return aisUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AisUser", "Id", id));
    }

    @Override
    public AisUser updateAisUser(AisUser aisUser, long id) {
        // check whether aisUser with given id is exist in DB or not
        AisUser existingAisUser = aisUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AisUser", "Id", id));
        existingAisUser.setUsername(aisUser.getUsername());
        existingAisUser.setFirstName(aisUser.getFirstName());
        existingAisUser.setPassword(encoder.encode(aisUser.getPassword()));
        existingAisUser.setBonusPoints(aisUser.getBonusPoints());
        for ( AisUserGroup group : aisUser.getAisUserGroups() ) {
            if (!existingAisUser.getAisUserGroups().contains(group)) {
                existingAisUser.getAisUserGroups().add(group);
            }
        }
        // save exsiting aisUser
        aisUserRepository.save(existingAisUser);
        return existingAisUser;
    }

    @Override
    public AisUser updateAisUserGroup(long aisUserId, long aisUserGroupId) {
        AisUser aisUser = getAisUserById(aisUserId);
        AisUserGroup aisUserGroup = aisUserGroupService.getAisUserGroupById(aisUserGroupId);
        if (!aisUser.getAisUserGroups().contains(aisUserGroup)) { aisUser.getAisUserGroups().add(aisUserGroup); }
        return aisUser;
    }

    @Override
    public void deleteAisUser(long id) {
        // check whether aisUser with given id is exist in DB or not
        aisUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AisUser", "Id", id));
        aisUserRepository.deleteById(id);
    }
}
