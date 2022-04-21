package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.beloglazov.dis.dis3.converters.WayConverter;
import ru.nsu.ccfit.beloglazov.dis.dis3.dto.WayDto;
import ru.nsu.ccfit.beloglazov.dis.dis3.repositories.WayRepository;
import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class WayServiceImpl implements WayService {

    private final WayRepository repository;

    @Override
    public WayDto findById(int wayId) {
        return WayConverter.entityToDto(repository.findById(wayId).orElse(null));
    }

    @Override
    public WayDto save(WayDto way) {
        return WayConverter.entityToDto(repository.save(WayConverter.dtoToEntity(way)));
    }

    @Override
    public void deleteById(int wayId) {
        repository.deleteById(wayId);
    }
}