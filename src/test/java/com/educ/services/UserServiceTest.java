package com.educ.services;

import com.educ.data.RoleRepository;
import com.educ.data.UserRepository;
import com.educ.entity.Role;
import com.educ.entity.User;
import com.educ.entity.Video;
import com.educ.services.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("test du user service")
public class UserServiceTest {
    @Autowired
    UserService userService;

     @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository mockedRoleRepository;

    @Autowired
    private RoleService roleService;

    private  List<User> users;

    @BeforeAll
    static void initAll() {
        System.out.println("beforeAll");
    }

    @Test
    @DisplayName("creation user test")
    public void testCreationUser(){
        //create data
        String firstName="Salsabil";
        String lastName="Grouche";
        LocalDate birthAt=LocalDate.of(1988,7,19);
        String urlImage="https://thumbs.dreamstime.com/z/ic%C3%B4ne-de-fille-avatar-femme-visage-type-dessin-anim%C3%A9-vecteur-89897086.jpg";
        String email="salsabilgrouche@yahoo.fr";
        String password="xxxx";
        String status="Developpeuse";

        User user=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        List<User> users=userService.findAll();
        assertNotNull(user);
        assertTrue(user.getEmail().equals("salsabilgrouche@yahoo.fr"));
        assertNotNull(users);
        assertEquals(users.size(),1);
        firstName="Ilyan";
        lastName="Guerilli";
        birthAt=LocalDate.of(2020,4,8);
        urlImage="https://i.unimedias.fr/2012/06/06/Bebe-a-2-mois1_0.jpg?auto=format%2Ccompress&crop=faces&cs=tinysrgb&fit=crop&h=453&w=806";
        email="salsabilgrouche@yahoo.fr";
        password="yyyy";
        status="Dev";
        User userJunior=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        assertNotNull(userJunior);
        users=userService.findAll();
        assertEquals(users.size(),1);
        email="ilyanIlyan@yahoo.fr";
        userJunior=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        assertNotNull(userJunior);
        users=userService.findAll();
        assertEquals(users.size(),2);

    }

    @Test
    @DisplayName("update user test")
    public void testUpdateUser(){
        String firstName="Salsabil";
        String lastName="Grouche";
        LocalDate birthAt=LocalDate.of(1988,7,19);
        String urlImage="https://thumbs.dreamstime.com/z/ic%C3%B4ne-de-fille-avatar-femme-visage-type-dessin-anim%C3%A9-vecteur-89897086.jpg";
        String email="salsabilgrouche@yahoo.fr";
        String password="xxxx";
        String status="Developpeuse";
        User user=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        List<User> users=userService.findAll();
        this.userService.updateUser(1L,"","",LocalDate.now(),"","dsd@gmail.com","","");
        List<User> usersUpdated=this.userService.findAll();
        assertEquals(users.size(),usersUpdated.size());
        assertTrue(usersUpdated.get(0).getFirstName().equals("")); ;
        this.userService.updateUser(1L,"xx","",LocalDate.now(),"",null,"","");
        usersUpdated=userService.findAll();
        assertFalse(usersUpdated.get(0).getFirstName().equals("xx"));
        this.userService.updateUser(8L,"","",LocalDate.now(),"","dsd@gmail.com","","");
        users=this.userService.findAll();
        assertTrue(users.size()==1 && users.get(0).getId()!=9L);
    }

    @Test
    @DisplayName("update user test")
    public void testUpdateByEmail(){
        String firstName="Salsabil";
        String lastName="Grouche";
        LocalDate birthAt=LocalDate.of(1988,7,19);
        String urlImage="https://thumbs.dreamstime.com/z/ic%C3%B4ne-de-fille-avatar-femme-visage-type-dessin-anim%C3%A9-vecteur-89897086.jpg";
        String email="salsabilgrouche@yahoo.fr";
        String password="xxxx";
        String status="Developpeuse";
        User user=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        List<User> users=userService.findAll();

        User userUpdated=this.userService.updateByEmail("Ilyan","Guerilli",birthAt,null,"salsabilgrouche@yahoo.fr","qqq","");
        List<User> usersUpdated=this.userService.findAll();
        assertEquals(users.size(),usersUpdated.size());
        assertTrue(userUpdated.getFirstName().equals("Ilyan")) ;
        this.userService.updateByEmail("A","B",birthAt,null,"cc@yahoo.fr","qqq","");
        usersUpdated=userService.findAll();
        assertFalse(usersUpdated.get(0).getFirstName().equals("A"));
        this.userService.updateByEmail("Ilyan","Guerilli",birthAt,null,null,"qqq","");
        users=this.userService.findAll();
        assertFalse(usersUpdated.get(0).getFirstName().equals("D"));
    }

    @Test
    @DisplayName("delete user test")
    public void testDeleteUser(){
        String firstName="Salsabil";
        String lastName="Grouche";
        LocalDate birthAt=LocalDate.of(1988,7,19);
        String urlImage="https://thumbs.dreamstime.com/z/ic%C3%B4ne-de-fille-avatar-femme-visage-type-dessin-anim%C3%A9-vecteur-89897086.jpg";
        String email="salsabilgrouche@yahoo.fr";
        String password="xxxx";
        String status="Developpeuse";
        User user=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        this.userService.deleteUser(1L);
        List<User> users=userService.findAll();
        assertEquals(users.size(),0);
        assertFalse(users.contains(user));
        email="gfff@gmail.com";
        user=this.userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status,1L);
        users=this.userService.findAll();
        assertEquals(users.size(),1);
        this.userService.deleteUser(10L);
        users=this.userService.findAll();
        assertTrue(users.contains(user));
    }
}

