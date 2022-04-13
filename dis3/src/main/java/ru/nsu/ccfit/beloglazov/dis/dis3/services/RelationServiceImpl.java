package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.beloglazov.dis.dis3.converters.RelationConverter;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.RelationDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.repositories.RelationRepository;

@AllArgsConstructor
@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository repository;

    @Override
    public RelationDto findById(int relationId) {
        return RelationConverter.entityToDto(repository.findById(relationId).orElse(null));
    }

    @Override
    public RelationDto save(RelationDto relation) {
        return RelationConverter.entityToDto(repository.save(RelationConverter.dtoToEntity(relation)));
    }

    @Override
    public void deleteById(int relationId) {
        repository.deleteById(relationId);
    }
}