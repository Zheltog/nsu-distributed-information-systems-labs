package ru.nsu.ccfit.beloglazov.dis.dis3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class WayDto {

    private List<Nd> nd;
    private List<TagDto> tag;
    private Integer id;
    private String usr;
    private BigInteger uid;
    private Boolean visible;
    private BigInteger version;
    private BigInteger changeset;
    private LocalDateTime timestamp;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Nd {
        private BigInteger ref;
    }
}