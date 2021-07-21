package org.learn365.user.web;

import org.learn365.user.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/role/")
public class RoleController {

    private RoleService roleservice;

    public RoleController(RoleService roleservice) {
        this.roleservice = roleservice;
    }

    @GetMapping(value = {"getallAvailableRole", "service/getallAvailableRole"})
    public List<String> getAllAvailableRole() {
        return roleservice.getAllRoleAvailable();
    }


}
