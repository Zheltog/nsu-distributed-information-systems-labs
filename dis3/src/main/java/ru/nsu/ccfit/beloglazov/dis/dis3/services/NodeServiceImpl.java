package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.NodeEntity;
import ru.nsu.ccfit.beloglazov.dis.dis3.repositories.NodeRepository;

@AllArgsConstructor
@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository repository;

    @Override
    public NodeEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }
}