package com.educ.services;

import com.educ.data.RoleRepository;
import com.educ.entity.Role;
import org.apache.catalina.LifecycleState;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("test du role service")
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

   @BeforeAll
    static void initAll() {
        System.out.println("beforeAll");
    }

/*
    @BeforeEach
    void init() {
        System.out.println("beforeEach");

    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("afterAll");
    }
*/
    @Test
    @DisplayName("creation role test")
    public void testCreationRole(){
        //create data
        String name="Admin";
        String n="455";
        Role role=this.roleService.createRole(name);
        List<Role> roles=roleService.findAll();
        assertNotNull(role);
        assertNotNull(roles);
        Role role_exist=this.roleService.createRole(name);
        roles=roleService.findAll();
        assertNull(role_exist);
        assertNotEquals(role,role_exist);

        Role role1=this.roleService.createRole(n);
        roles=roleService.findAll();

        assertNotNull(role1);
        assertEquals(roles.size(),2);
        assertNotEquals(roles.size(),3);
   }

}
