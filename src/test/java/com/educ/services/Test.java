package com.educ.services;

import com.educ.data.UserRepository;
import com.educ.entity.User;
import com.educ.services.dto.UserDTO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class Test {
    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository mockedUserRepository;

    @org.junit.jupiter.api.Test
    public void testCreateUser(){
        /* comportamiento */
        when(mockedUserRepository.save(Mockito.any(User.class))).thenReturn(new User(null,null,null,null,"wendy@hotmail.fr",null,null));
        /* on appele le service */
        UserDTO userDTO=new UserDTO(null,null,null,null,"wendy@hotmail.fr",null,null);
        User testUser = userService.createUser(userDTO);
        /* test null */
        assertNotNull(testUser);
        /* test id not null */
        assertEquals(testUser.getId(), null);
        /* test objet not null */
        assertThat(testUser).isNotNull();
        /* test objet id not null */
        assertThat(testUser.getId()).isNull();
        /* si les references sont egales */
        assertThat(testUser).isEqualTo(new User(null,null,null,null,"wendy@hotmail.fr",null,null));
        //assertThat(testUser).isSameAs(new User(null,null,null,null,"wendy@hotmail.fr",null,null));
        /* si tous les champs sont ok par rapport au premier argument */
        assertThat(testUser).usingRecursiveComparison().isEqualTo(new User(null,null,null,null,"wendy@hotmail.fr",null,null));
        /* Verifie une fois l'utilisation de repo mocked avec un user mocked */
        verify(mockedUserRepository, times(1)).save(any(User.class));
    }
}
