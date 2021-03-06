package ru.nsu.ccfit.beloglazov.dis.dis3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    TagEntity findByNodeIdAndK(Integer nodeId, String k);

    void deleteByNodeIdAndK(Integer nodeId, String k);
}