package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@IdClass(TagId.class)
@Table(name = "tags")
@Data
@NoArgsConstructor
public class TagEntity {

    @Id
    @Column(nullable = false)
    private Integer nodeId;

    @Id
    @Column
    private String k;

    @Column
    private String v;
}