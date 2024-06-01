package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.AisUserGroup;
import com.aiscoworking.aiscoworking.service.AisUserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aisUserGroup")
public class AisUserGroupController {
    private final AisUserGroupService aisUserGroupService;

    public AisUserGroupController(AisUserGroupService aisUserGroupService) {
        super();
        this.aisUserGroupService = aisUserGroupService;
    }

    // build create AisUserGroup Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<AisUserGroup> saveAisUserGroup(@RequestBody AisUserGroup aisUserGroup) {
        return new ResponseEntity<AisUserGroup>(aisUserGroupService.saveAisUserGroup(aisUserGroup),
                HttpStatus.CREATED);
    }

    // build get all AisUserGroups Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER')")
    @GetMapping()
    public List<AisUserGroup> getAllAisUserGroups() {
        return aisUserGroupService.getAllAisUserGroups();
    }

    // build get AisUserGroup by ID Rest Api
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER')")
    public ResponseEntity<AisUserGroup> getAisUserGroupById(@PathVariable("id") long id) {
        return new ResponseEntity<AisUserGroup>(aisUserGroupService.getAisUserGroupById(id), HttpStatus.OK);
    }

    // build update AisUserGroup Rest Api
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AisUserGroup> updateAisUserGroup(@PathVariable("id") long id,
                                                           @RequestBody AisUserGroup aisUserGroup) {
        return new ResponseEntity<AisUserGroup>(aisUserGroupService.updateAisUserGroup(aisUserGroup, id),
                HttpStatus.OK);
    }
}
