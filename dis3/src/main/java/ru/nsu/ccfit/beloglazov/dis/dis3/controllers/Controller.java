package ru.nsu.ccfit.beloglazov.dis.dis3.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.NodeEntity;
import ru.nsu.ccfit.beloglazov.dis.dis3.services.NodeService;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Log
public class Controller {

    private final NodeService nodeService;

    @GetMapping("/node/{id}")
    public NodeEntity findNode(@PathVariable int id) {
        log.info("Categories :: handling :: findNode");
        return nodeService.findById(id);
    }
}