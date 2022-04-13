package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nodes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeEntity {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(precision = 10)
    private Double lat;

    @Column(precision = 10)
    private Double lon;

    @Column
    private String usr;

    @Column
    private BigInteger uid;

    @Column(columnDefinition = "BIT(1)")
    private Boolean visible;

    @Column
    private BigInteger version;

    @Column
    private BigInteger changeset;

    @Column
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "nodeId")
    private List<TagEntity> tags;
}