package ru.nsu.ccfit.beloglazov.dis.dis3.converters;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagEntity;

public class TagConverter {

    public static TagDto entityToDto(TagEntity entity) {
        if (entity == null) return null;
        return new TagDto(entity.getNodeId(), entity.getK(), entity.getV());
    }

    public static TagEntity dtoToEntity(TagDto dto) {
        if (dto == null) return null;
        return new TagEntity(dto.getNodeId(), dto.getK(), dto.getV());
    }
}