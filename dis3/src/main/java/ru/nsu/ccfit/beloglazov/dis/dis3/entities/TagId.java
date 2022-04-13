package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagId implements Serializable {
    private Integer nodeId;
    private String k;
}