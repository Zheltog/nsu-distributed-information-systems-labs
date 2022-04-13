package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.beloglazov.dis.dis3.converters.NodeConverter;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.NodeDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.repositories.NodeRepository;

@AllArgsConstructor
@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository repository;

    @Override
    public NodeDto findById(int nodeId) {
        return NodeConverter.entityToDto(repository.findById(nodeId).orElse(null));
    }

    @Override
    public NodeDto save(NodeDto nodeDto) {
        return NodeConverter.entityToDto(repository.save(NodeConverter.dtoToEntity(nodeDto)));
    }

    @Override
    public void deleteById(int nodeId) {
        repository.deleteById(nodeId);
    }
}