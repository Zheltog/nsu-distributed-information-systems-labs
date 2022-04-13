package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.NodeDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.RangeDto;
import java.util.List;

public interface NodeService {

    NodeDto findById(int nodeId);

    NodeDto save(NodeDto node);

    void deleteById(int nodeId);

    List<NodeDto> findInRange(RangeDto range);
}