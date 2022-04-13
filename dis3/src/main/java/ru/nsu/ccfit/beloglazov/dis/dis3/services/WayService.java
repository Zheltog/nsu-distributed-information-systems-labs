package ru.nsu.ccfit.beloglazov.dis.dis3.services;

import ru.nsu.ccfit.beloglazov.dis.dis3.dto.WayDto;

public interface WayService {

    WayDto findById(int wayId);

    WayDto save(WayDto way);

    void deleteById(int wayId);
}