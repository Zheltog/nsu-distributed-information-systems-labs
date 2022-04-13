package ru.nsu.ccfit.beloglazov.dis.dis3.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.RelationDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.services.RelationService;

@RestController
@RequestMapping("/relation")
@AllArgsConstructor
@Log
public class RelationController {

    private final RelationService relationService;

    @GetMapping("/find/{id}")
    public RelationDto findById(@PathVariable int id) {
        log.info("Relation :: handling :: findById");
        return relationService.findById(id);
    }

    @PostMapping("/save")
    public RelationDto save(@RequestBody RelationDto relation) {
        log.info("Relation :: handling :: save");
        return relationService.save(relation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("Relation :: handling :: deleteById");
        relationService.deleteById(id);
    }
}