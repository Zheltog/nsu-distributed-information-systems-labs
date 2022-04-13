package ru.nsu.ccfit.beloglazov.dis.dis3.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.WayDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.services.WayService;

@RestController
@RequestMapping("/way")
@AllArgsConstructor
@Log
public class WayController {

    private final WayService wayService;

    @GetMapping("/find/{id}")
    public WayDto findById(@PathVariable int id) {
        log.info("Way :: handling :: findById");
        return wayService.findById(id);
    }

    @PostMapping("/save")
    public WayDto save(@RequestBody WayDto way) {
        log.info("Way :: handling :: save");
        return wayService.save(way);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("Way :: handling :: deleteById");
        wayService.deleteById(id);
    }
}