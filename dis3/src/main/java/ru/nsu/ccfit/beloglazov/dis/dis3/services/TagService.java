package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.TagDto;

public interface TagService {

    TagDto findByNodeIdAndKey(int nodeId, String k);

    TagDto save(TagDto tag);

    void deleteByNodeIdAndKey(int nodeId, String k);
}