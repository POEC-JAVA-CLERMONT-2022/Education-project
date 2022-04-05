package com.educ.web;

import com.educ.data.ModuleeRepository;
import com.educ.entity.Modulee;
import com.educ.services.ModuleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Modulee addModule(){
        Modulee modulee = new Modulee("Module API");
        return moduleeRepository.save(modulee);
    }
/*
    @PutMapping("modules/{id}")
    public

 */
}
