package ru.nsu.ccfit.beloglazov.dis.dis3.converters;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.NodeDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.NodeEntity;

public class NodeConverter {

    public static NodeDto entityToDto(NodeEntity entity) {
        if (entity == null) return null;
        return new NodeDto(
                entity.getId(),
                entity.getLat(),
                entity.getLon(),
                entity.getUsr(),
                entity.getUid(),
                entity.getVisible(),
                entity.getVersion(),
                entity.getChangeset(),
                entity.getTimestamp()
        );
    }

    public static NodeEntity dtoToEntity(NodeDto dto) {
        if (dto == null) return null;
        return new NodeEntity(
                dto.getId(),
                dto.getLat(),
                dto.getLon(),
                dto.getUsr(),
                dto.getUid(),
                dto.getVisible(),
                dto.getVersion(),
                dto.getChangeset(),
                dto.getTimestamp()
        );
    }
}