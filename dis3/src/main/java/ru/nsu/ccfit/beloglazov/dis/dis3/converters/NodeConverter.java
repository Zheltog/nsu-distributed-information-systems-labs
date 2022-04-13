package ru.nsu.ccfit.beloglazov.dis.dis3.converters;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.NodeDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.NodeEntity;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagEntity;
import java.util.LinkedList;
import java.util.List;

public class NodeConverter {

    public static NodeDto entityToDto(NodeEntity entity) {
        if (entity == null) return null;
        List<TagDto> tags = new LinkedList<>();
        if (entity.getTags() != null) {
            for (TagEntity tag : entity.getTags()) {
                tags.add(TagConverter.entityToDto(tag));
            }
        }
        return new NodeDto(
                entity.getId(),
                entity.getLat(),
                entity.getLon(),
                entity.getUsr(),
                entity.getUid(),
                entity.getVisible(),
                entity.getVersion(),
                entity.getChangeset(),
                entity.getTimestamp(),
                tags
        );
    }

    public static NodeEntity dtoToEntity(NodeDto dto) {
        if (dto == null) return null;
        List<TagEntity> tags = new LinkedList<>();
        if (dto.getTags() != null) {
            for (TagDto tag : dto.getTags()) {
                tags.add(TagConverter.dtoToEntity(tag));
            }
        }
        return new NodeEntity(
                dto.getId(),
                dto.getLat(),
                dto.getLon(),
                dto.getUsr(),
                dto.getUid(),
                dto.getVisible(),
                dto.getVersion(),
                dto.getChangeset(),
                dto.getTimestamp(),
                tags
        );
    }

    public static List<NodeDto> entityListToDtoList(List<NodeEntity> entities) {
        List<NodeDto> result = new LinkedList<>();
        if (entities != null) {
            for (NodeEntity entity: entities) {
                result.add(entityToDto(entity));
            }
        }
        return result;
    }
}