package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.beloglazov.dis.dis3.converters.TagConverter;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.repositories.TagRepository;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Override
    public TagDto findByNodeIdAndKey(int nodeId, String k) {
        return TagConverter.entityToDto(repository.findByNodeIdAndK(nodeId, k));
    }

    @Override
    public TagDto save(TagDto tag) {
        return TagConverter.entityToDto(repository.save(TagConverter.dtoToEntity(tag)));
    }

    @Override
    public void deleteByNodeIdAndKey(int nodeId, String k) {
        repository.deleteByNodeIdAndK(nodeId, k);
    }
}