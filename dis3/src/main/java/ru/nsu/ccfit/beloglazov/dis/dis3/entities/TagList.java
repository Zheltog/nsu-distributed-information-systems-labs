package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagList implements Serializable {
    private List<TagEntity> tags;
}