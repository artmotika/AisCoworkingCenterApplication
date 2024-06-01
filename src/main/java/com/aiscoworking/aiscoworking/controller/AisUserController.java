package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.AisUser;
import com.aiscoworking.aiscoworking.model.AisUserGroup;
import com.aiscoworking.aiscoworking.repository.AisUserGroupRepository;
import com.aiscoworking.aiscoworking.service.AisUserGroupService;
import com.aiscoworking.aiscoworking.service.AisUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("api/aisUsers")
public class AisUserController {

    private final AisUserService aisUserService;
    private final AisUserGroupService aisUserGroupService;

    public AisUserController(AisUserService aisUserService, AisUserGroupService aisUserGroupService) {
        super();
        this.aisUserService = aisUserService;
        this.aisUserGroupService = aisUserGroupService;
    }

    // build create AisUser Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PostMapping("{group_id}")
    public ResponseEntity<AisUser> saveAisUser(@PathVariable("group_id") long group_id, @RequestBody AisUser aisUser) {
        AisUserGroup aisUserGroup = aisUserGroupService.getAisUserGroupById(group_id);
        aisUser.getAisUserGroups().add(aisUserGroup);
        return new ResponseEntity<AisUser>(aisUserService.saveAisUser(aisUser), HttpStatus.CREATED);
    }

    // build create AisUser Rest Api
    @PostMapping("register")
    public ResponseEntity<AisUser> saveAisUser(@RequestBody AisUser aisUser) {
        AisUserGroup aisUserGroup = aisUserGroupService.getAisUserGroupById(1);
        aisUser.getAisUserGroups().add(aisUserGroup);
        return new ResponseEntity<AisUser>(aisUserService.saveAisUser(aisUser), HttpStatus.CREATED);
    }

    // build get all AisUsers Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<AisUser> getAllAisUsers() {
        return aisUserService.getAllAisUsers();
    }

    // build get AisUser by ID Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<AisUser> getAisUserById(@PathVariable("id") long id) {
        return new ResponseEntity<AisUser>(aisUserService.getAisUserById(id), HttpStatus.OK);
    }

    // build update AisUser Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PutMapping("{id}/{group_id}")
    public ResponseEntity<AisUser> updateAisUser(@PathVariable("id") long id,
                                                 @PathVariable("group_id") long group_id,
                                                 @RequestBody AisUser aisUser) {
        AisUserGroup aisUserGroup = aisUserGroupService.getAisUserGroupById(group_id);
        if (!aisUser.getAisUserGroups().contains(aisUserGroup)) { aisUser.getAisUserGroups().add(aisUserGroup); }
        return new ResponseEntity<AisUser>(aisUserService.updateAisUser(aisUser, id), HttpStatus.OK);
    }

    // build update AisUserGroup for AisUser Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER')")
    @PutMapping("admin/{id}/{group_id}")
    public ResponseEntity<AisUser> updateAisUserGroup(@PathVariable("id") long id,
                                                 @PathVariable("group_id") long group_id) {
        return new ResponseEntity<AisUser>(aisUserService.updateAisUserGroup(id, group_id), HttpStatus.OK);
    }

    // build delete AisUser Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAisUser(@PathVariable("id") long id) {
        // delete AisUser from DB
        aisUserService.deleteAisUser(id);
        return new ResponseEntity<String>("AisUser deleted successfully!", HttpStatus.OK);
    }
}
