package ru.nsu.ccfit.beloglazov.dis.dis3.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.services.TagService;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
@Log
public class TagController {

    private final TagService tagService;

    @GetMapping("/find/{nodeId}/{key}")
    public TagDto findByNodeIdAndKey(@PathVariable int nodeId, @PathVariable String key) {
        log.info("Tag :: handling :: findByNodeIdAndKey");
        return tagService.findByNodeIdAndKey(nodeId, key);
    }

    @PostMapping("/save")
    public TagDto save(@RequestBody TagDto tag) {
        log.info("Tag :: handling :: save");
        return tagService.save(tag);
    }

    @DeleteMapping("/delete/{nodeId}/{key}")
    public void deleteByNodeIdAndKey(@PathVariable int nodeId, @PathVariable String key) {
        log.info("Tag :: handling :: deleteByNodeIdAndKey");
        tagService.deleteByNodeIdAndKey(nodeId, key);
    }
}