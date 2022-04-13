package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.RelationDto;

public interface RelationService {

    RelationDto findById(int relationId);

    RelationDto save(RelationDto relation);

    void deleteById(int relationId);
}