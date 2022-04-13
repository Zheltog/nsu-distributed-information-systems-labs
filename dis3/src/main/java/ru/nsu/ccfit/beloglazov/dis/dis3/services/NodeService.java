package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.NodeDto;

public interface NodeService {

    NodeDto findById(int nodeId);

    NodeDto save(NodeDto node);

    void deleteById(int nodeId);
}