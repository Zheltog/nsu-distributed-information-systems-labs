package ru.nsu.ccfit.beloglazov.dis.dis3.converters;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.WayDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagEntity;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagList;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.WayEntity;
import java.util.LinkedList;
import java.util.List;

public class WayConverter {

    public static WayDto entityToDto(WayEntity entity) {
        if (entity == null) return null;
        List<WayDto.Nd> nds = new LinkedList<>();
        for (WayEntity.Nd nd : entity.getNd().getNds()) {
            nds.add(new WayDto.Nd(nd.getRef()));
        }
        List<TagDto> tags = new LinkedList<>();
        for (TagEntity tag : entity.getTag().getTags()) {
            tags.add(TagConverter.entityToDto(tag));
        }
        return new WayDto(
                nds,
                tags,
                entity.getId(),
                entity.getUsr(),
                entity.getUid(),
                entity.getVisible(),
                entity.getVersion(),
                entity.getChangeset(),
                entity.getTimestamp()
        );
    }

    public static WayEntity dtoToEntity(WayDto dto) {
        if (dto == null) return null;
        List<WayEntity.Nd> nds = new LinkedList<>();
        if (dto.getNd() != null) {
            for (WayDto.Nd nd : dto.getNd()) {
                nds.add(new WayEntity.Nd(nd.getRef()));
            }
        }
        List<TagEntity> tags = new LinkedList<>();
        if (dto.getTag() != null) {
            for (TagDto tag : dto.getTag()) {
                tags.add(TagConverter.dtoToEntity(tag));
            }
        }
        return new WayEntity(
                new WayEntity.NdList(nds),
                new TagList(tags),
                dto.getId(),
                dto.getUsr(),
                dto.getUid(),
                dto.getVisible(),
                dto.getVersion(),
                dto.getChangeset(),
                dto.getTimestamp()
        );
    }
}