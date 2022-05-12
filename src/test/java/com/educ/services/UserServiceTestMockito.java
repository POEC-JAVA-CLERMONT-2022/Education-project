package com.educ.services;

import com.educ.data.RoleRepository;
import com.educ.data.UserRepository;
import com.educ.entity.Role;
import com.educ.entity.User;
import com.educ.services.dto.RoleDTO;
import com.educ.services.dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("test du user service")
public class UserServiceTestMockito {

    @InjectMocks
    UserService userService;


    @Mock
    private UserRepository mockedUserRepository;

/*    @Mock
    private UserService mockedUserService;*/
    @Mock
    private RoleService mockedRoleService;



    @Mock
    private RoleRepository mockedRoleRepository;

    @InjectMocks
    private RoleService roleService;

    private List<User> users;


    private User createUser(){
        String firstName="Salsabil";
        String lastName="Grouche";
        LocalDate birthAt=LocalDate.of(1988,7,19);
        String urlImage="https://thumbs.dreamstime.com/z/ic%C3%B4ne-de-fille-avatar-femme-visage-type-dessin-anim%C3%A9-vecteur-89897086.jpg";
        String email="salsabilgrouche@yahoo.fr";
        String password="xxxx";
        String status="Developpeuse";

        List<Role> roles=new LinkedList<Role>();
        roles.add(new Role("Member"));
        List<RoleDTO> roleDTOS=new LinkedList<RoleDTO>();

        for (Role r:roles){
            roleDTOS.add(new RoleDTO(r.getId(),r.getName()));
        }
        UserDTO userDTO=new UserDTO(1L,firstName,lastName,birthAt,urlImage,email,password,status, roleDTOS);

        User user=new User(firstName,lastName,birthAt,urlImage,email,password,status);
        user.setRoles(roles);
        return user;
    }


    @Test
    @DisplayName("creation user test")
    public void testCreationUser(){
        //ARRANGE
        String firstName="Salsabil";
        String lastName="Grouche";
        LocalDate birthAt=LocalDate.of(1988,7,19);
        String urlImage="https://thumbs.dreamstime.com/z/ic%C3%B4ne-de-fille-avatar-femme-visage-type-dessin-anim%C3%A9-vecteur-89897086.jpg";
        String email="salsabilgrouche@yahoo.fr";
        String password="xxxx";
        String status="Developpeuse";
        //UserDTO userDTO=new UserDTO(1L,firstName,lastName,birthAt,urlImage,email,password,status);
        List<Role> roles=new LinkedList<Role>();
        roles.add(new Role("Member"));

        List<RoleDTO> roleDTOS=new LinkedList<RoleDTO>();

        for (Role r:roles){
            roleDTOS.add(new RoleDTO(r.getId(),r.getName()));
        }
        UserDTO userDTO=new UserDTO(1L,firstName,lastName,birthAt,urlImage,email,password,status, roleDTOS);
        users=new LinkedList<User>();

        when(mockedRoleRepository.save(Mockito.any(Role.class))).thenReturn(new Role("Member"));
        when(mockedRoleService.findByName(Mockito.any(String.class))).thenReturn(new Role("Member"));
        when(mockedUserRepository.save(Mockito.any(User.class))).thenReturn(this.createUser());
        when(mockedUserRepository.findAll()).thenReturn(users);
        when(mockedRoleRepository.findAll()).thenReturn(roles);

        //ACT
        Role testRole=roleService.createRole("Member");
        List<Role> rs=new LinkedList<Role>();
        rs.add(testRole);
        User testUser = userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status);

        //ASSERT
        assertNotNull(testUser);
        assertEquals(testUser.getId(), null);
        assertThat(testUser).isNotNull();
        assertThat(testUser.getId()).isNull();
        assertThat(testUser).isEqualTo(new User(firstName,lastName,birthAt,urlImage,email,password,status));

        //ARRANGE
        firstName="Ilyan";
        lastName="Guerilli";
        birthAt=LocalDate.of(2020,4,8);
        urlImage="https://i.unimedias.fr/2012/06/06/Bebe-a-2-mois1_0.jpg?auto=format%2Ccompress&crop=faces&cs=tinysrgb&fit=crop&h=453&w=806";
        email="salsabilgrouche@yahoo.fr";
        password="yyyy";
        status="Dev";

        when(mockedUserRepository.save(Mockito.any(User.class))).thenReturn(null);

        //ACT
        User userJunior = userService.createUser(firstName,lastName,birthAt,urlImage,email,password,status);

        //ASSERT
        assertThat(userJunior).isNull();

        /* Verifie l'utilisation de repo mocked avec un user et role mocked */
        verify(mockedRoleRepository, times(1)).save(any(Role.class));
        verify(mockedUserRepository, times(2)).save(any(User.class));
    }


}
