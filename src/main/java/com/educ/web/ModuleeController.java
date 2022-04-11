package com.educ.web;

import com.educ.data.ModuleeRepository;
import com.educ.entity.Modulee;
import com.educ.services.ModuleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
public class ModuleeController {
    @Autowired
    private ModuleeService moduleeService;

    @Autowired
    private ModuleeRepository moduleeRepository;

    @GetMapping("/modules")
    public List<Modulee> getModules(){
        List<Modulee> modulees = moduleeRepository.findAll();
        return modulees;
    }

    @GetMapping("modules/{id}")
    public Modulee getModuleById(@PathVariable Long id){
        Modulee modulee = moduleeRepository.getById(id);
        return modulee;
    }

    @PostMapping("modules/add")
    public Modulee addModule(@RequestBody Modulee modulee){
        return moduleeRepository.save(modulee);
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

    @PutMapping("modules/{id}")
    public Modulee updateModule(@PathVariable Long id, @RequestParam String title){
        Modulee modulee = moduleeService.updateModule(id, title);
        return modulee;
    }

    @DeleteMapping("modules/{id}")
    public void deleteModule(@PathVariable Long id){
        this.moduleeRepository.deleteById(id);
    }


}
