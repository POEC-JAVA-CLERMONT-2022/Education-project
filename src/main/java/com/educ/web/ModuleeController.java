package com.educ.web;

import com.educ.entity.Language;
import com.educ.entity.Level;
import com.educ.entity.Modulee;
import com.educ.entity.User;
import com.educ.services.ModuleeService;
import com.educ.services.dto.ModuleeDTO;
import com.educ.services.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleeController {

    Logger logger = LoggerFactory.getLogger(ModuleeController.class);

    private ModuleeService moduleeService;

    @Autowired
    public ModuleeController(ModuleeService moduleeService) {
        this.moduleeService = moduleeService;
    }

    @GetMapping()
    public ResponseEntity<?> getModules() {
        try {
            List<Modulee> modulees = moduleeService.findAll();
           return new ResponseEntity<>(modulees, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found", e);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Long id) {
        try {
            logger.info("Module {}", id);
            Modulee findModulee = moduleeService.getById(id);
            if(findModulee == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            ModuleeDTO moduleeDTO=new ModuleeDTO();

            return new ResponseEntity<>(moduleeDTO.convertTo(findModulee), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found");
        }
    }

    /*
    @PostMapping()
    public ResponseEntity<?> addModule(@RequestBody Modulee modulee) {
        try {
            if(modulee.getTitle() == null || modulee.getTitle() == ""){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Modulee createdModulee = moduleeService.createModule(modulee.getTitle(), modulee.getVideo());
            return new ResponseEntity(createdModulee, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create module", e);
        }
    }

     */

    @PutMapping("{id}")
    public ResponseEntity<?> updateModulee(@PathVariable Long id, @RequestBody Modulee modulee) {
        try {
           if(id != null){
               logger.info("Modulee : {}", id);
               moduleeService.updateModule(id, modulee.getTitle());
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.info("Modulee : {}", id);
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update module");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {
        try {
            Modulee modulee = moduleeService.getById(id);
            if(modulee == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            moduleeService.deleteModule(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found");

        }
    }
}