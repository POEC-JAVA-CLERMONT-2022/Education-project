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
    public ResponseEntity<List<ModuleeDTO>> getModules() {
        try {
            List<Modulee> modulees = moduleeService.findAll();
            List<ModuleeDTO> moduleeDTOS = new LinkedList<ModuleeDTO>();
            for (Modulee modulee : modulees) {
                ModuleeDTO moduleeDTO = new ModuleeDTO();
                moduleeDTOS.add(moduleeDTO.convertTo(modulee));
            }
            return new ResponseEntity<>(moduleeDTOS, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found", e);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ModuleeDTO> getModuleById(@PathVariable Long id) {
        try {
            logger.info("Module {}", id);
            ModuleeDTO moduleeDTO = new ModuleeDTO();
            moduleeDTO.convertTo(moduleeService.getById(id));
            return new ResponseEntity<>(moduleeDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found");
        }
    }

    @PostMapping("add")
    public ResponseEntity<ModuleeDTO> addModule(@RequestBody ModuleeDTO moduleeDTO) {
        try {
            ModuleeDTO createdModulee = new ModuleeDTO();
            Modulee modulee = moduleeService.createModule(moduleeDTO.getTitle(), "Java", Level.MIDDLE, Language.EN, "www.you.com");
            createdModulee.convertTo(modulee);
            return new ResponseEntity(createdModulee, HttpStatus.CREATED); /* OK*/
        } catch (Exception e) { /* check when email exist */
            e.printStackTrace();
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create module", e);
        }
    }

    @PutMapping("{id}")
    public void updateModulee(@PathVariable Long id, @RequestBody ModuleeDTO moduleeDTO) {
        try {
            logger.info("Modulee : {}", id);
            //UserDTO userDTOLocale = new UserDTO();
            //LocalDate localDate=userDTO.getBirthAt();
            moduleeService.updateModule(id, moduleeDTO.getTitle());
        } catch (Exception e) {
            logger.info("Modulee : {}", id);
            e.printStackTrace();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Modulee> deleteModule(@PathVariable Long id) {
        try {
            moduleeService.deleteModule(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found");

        }
    }
}