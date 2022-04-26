package com.educ.services;

import com.educ.data.ModuleeRepository;
import com.educ.entity.Language;
import com.educ.entity.Level;
import com.educ.entity.Modulee;
import com.educ.entity.Video;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("test du module service")
public class ModuleeServiceTest {
    @Autowired
    ModuleeService moduleeService;

    @Autowired
    ModuleeRepository moduleeRepository;

    @BeforeAll
    static void initAll() {
        System.out.println("beforeAll");
    }

    @Test
    @DisplayName("creation module test")
    public void testCreationModule(){
        //create data
        String title="Spring Boot";
        Modulee modulee=this.moduleeService.createModule(title,"", Level.ADVANCE, Language.EN,1L);
        List<Modulee> modulees=moduleeService.findAll();
        assertNotNull(modulee);
        assertTrue(modulee.getTitle().equals("Spring Boot") );
        assertNotNull(modulees);
        assertEquals(modulees.size(),1);
        Modulee moduleeSecond=this.moduleeService.createModule(title,"", Level.ADVANCE, Language.EN,1L);
        assertNull(moduleeSecond);
    }

    @Test
    @DisplayName("update module test")
    public void testUpdateModule(){
        String title="Spring Boot";
        Modulee modulee=this.moduleeService.createModule(title, "", Level.ADVANCE, Language.EN,1L);
        List<Modulee> modulees=moduleeService.findAll();
        Modulee moduleeUp=this.moduleeService.updateModule(1L,"JAVA");
        assertTrue(moduleeUp.getTitle().equals("JAVA")) ;
        moduleeUp=this.moduleeService.updateModule(1L, null);
        assertNull(moduleeUp);
        moduleeUp=this.moduleeService.updateModule(9L, "SQL");
        assertNull(moduleeUp);
        modulees=moduleeService.findAll();
        assertTrue(modulees.get(0).getTitle().equals("JAVA"));
        assertTrue(modulees.size()==1);
    }


    @Test
    @DisplayName("delete module test")
    public void testDeleteModule(){
        String title="Spring Boot";
        Modulee modulee=this.moduleeService.createModule(title,"", Level.ADVANCE, Language.EN,1L);
        List<Modulee> modulees=moduleeService.findAll();
        assertTrue(modulees.contains(modulee));
        this.moduleeService.deleteModule(1L);
        modulees=moduleeService.findAll();
        assertEquals(modulees.size(),0);
        assertFalse(modulees.contains(modulee));
        modulee=this.moduleeService.createModule(title, "", Level.ADVANCE, Language.EN,1L);
        modulees=moduleeService.findAll();
        this.moduleeService.deleteModule(10L);
        modulees=moduleeService.findAll();
        assertTrue(modulees.contains(modulee));
    }

}
