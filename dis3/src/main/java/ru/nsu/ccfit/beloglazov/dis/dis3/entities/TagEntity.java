package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo = "tags")
@Data
@NoArgsConstructor
public class TagEntity {
}