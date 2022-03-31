package com.educ.services;

import com.educ.data.RoleRepository;
import com.educ.entity.Role;
import org.apache.catalina.LifecycleState;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

@SpringBootTest
@DisplayName("test du role service")
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;


   /*   @BeforeAll  */

    @Test
    @DisplayName("creation role test")
    public void testCreationRole(){
        //create data
        String name="Admin";
        Role role=this.roleService.createRole(name);
        List<Role> roles=roleService.findAll();

        assertNull(role);
        assertNull(roles);

        Role role_exist=this.roleService.createRole(name);
        roles=roleService.findAll();

        assertNull(role);
        assertNull(roles);
   }

}
