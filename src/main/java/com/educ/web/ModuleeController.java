package com.educ.web;

import com.educ.data.ModuleeRepository;
import com.educ.entity.Language;
import com.educ.entity.Level;
import com.educ.entity.Modulee;
import com.educ.services.ModuleeService;
import com.educ.services.dto.ModuleeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleeController {

    private ModuleeService moduleeService;

    @Autowired
    public ModuleeController(ModuleeService moduleeService){
        this.moduleeService = moduleeService;
    }

    @GetMapping()
    public List<Modulee> getModules(){
        List<Modulee> modulees = moduleeService.findAll();
        return modulees;
    }

    @GetMapping("{id}")
    public Modulee getModuleById(@PathVariable Long id){
        Modulee modulee = moduleeService.getById(id);
        return modulee;
    }

    @PostMapping("add")
    public Modulee addModule(@RequestBody ModuleeDTO moduleeDTO){
        Modulee modulee= moduleeService.createModule(moduleeDTO.getTitle(), "Java", Level.MIDDLE, Language.EN,1L);
        return modulee;
    }

    //check this for update another attrib
    /*
    @PutMapping("modules/{id}")
    public Modulee updateModule(@PathVariable Long id, @RequestBody Modulee moduleeInfo){

        Modulee modulee = moduleeRepository.getById(id);
        modulee.setTitle(moduleeInfo.getTitle());
        this.moduleeRepository.save(modulee);
        return modulee;
    }

     */

    @PutMapping("{id}")
    public Modulee updateModule(@PathVariable Long id, @RequestBody String title){
        Modulee modulee = moduleeService.updateModule(id, title);
        return modulee;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Modulee> deleteModule(@PathVariable Long id){
        moduleeService.deleteModule(id);
        return ResponseEntity.ok().build();
    }


}
