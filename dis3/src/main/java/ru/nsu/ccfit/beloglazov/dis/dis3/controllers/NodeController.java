package ru.nsu.ccfit.beloglazov.dis.dis3.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.NodeDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.RangeDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.services.NodeService;
import java.util.List;

@RestController
@RequestMapping("/node")
@AllArgsConstructor
@Log
public class NodeController {

    private final NodeService nodeService;

    @GetMapping("/find/{id}")
    public NodeDto findById(@PathVariable int id) {
        log.info("Node :: handling :: findById");
        return nodeService.findById(id);
    }

    @PostMapping("/save")
    public NodeDto save(@RequestBody NodeDto node) {
        log.info("Node :: handling :: save");
        return nodeService.save(node);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("Node :: handling :: deleteById");
        nodeService.deleteById(id);
    }

    @GetMapping("/range")
    public List<NodeDto> findInRange(@RequestBody RangeDto range) {
        log.info("Node :: handling :: findInRange");
        return nodeService.findInRange(range);
    }
}