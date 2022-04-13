package ru.nsu.ccfit.beloglazov.dis.dis3.converters;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.RelationDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagEntity;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagList;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.RelationEntity;
import java.util.LinkedList;
import java.util.List;

public class RelationConverter {

    public static RelationDto entityToDto(RelationEntity entity) {
        if (entity == null) return null;
        List<RelationDto.Member> members = new LinkedList<>();
        for (RelationEntity.Member member : entity.getMember().getMembers()) {
            members.add(new RelationDto.Member(
                    member.getType(), member.getRef(), member.getRole()
            ));
        }
        List<TagDto> tags = new LinkedList<>();
        for (TagEntity tag : entity.getTag().getTags()) {
            tags.add(TagConverter.entityToDto(tag));
        }
        return new RelationDto(
                members,
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

    public static RelationEntity dtoToEntity(RelationDto dto) {
        if (dto == null) return null;
        List<RelationEntity.Member> members = new LinkedList<>();
        if (dto.getMember() != null) {
            for (RelationDto.Member member : dto.getMember()) {
                members.add(new RelationEntity.Member(
                        member.getType(), member.getRef(), member.getRole()
                ));
            }
        }
        List<TagEntity> tags = new LinkedList<>();
        if (dto.getTag() != null) {
            for (TagDto tag : dto.getTag()) {
                tags.add(TagConverter.dtoToEntity(tag));
            }
        }
        return new RelationEntity(
                new RelationEntity.MemberList(members),
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