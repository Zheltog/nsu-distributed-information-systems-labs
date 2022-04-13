package ru.nsu.ccfit.beloglazov.dis.dis3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDto {
    private Integer nodeId;
    private String k;
    private String v;
}