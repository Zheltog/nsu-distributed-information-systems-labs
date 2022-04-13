package ru.nsu.ccfit.beloglazov.dis.dis3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NodeDto {
    private Integer id;
    private Double lat;
    private Double lon;
    private String usr;
    private BigInteger uid;
    private Boolean visible;
    private BigInteger version;
    private BigInteger changeset;
    private LocalDateTime timestamp;
}