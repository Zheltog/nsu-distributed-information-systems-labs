package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(TagId.class)
@Table(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer nodeId;

    @Id
    @Column
    private String k;

    @Column
    private String v;
}