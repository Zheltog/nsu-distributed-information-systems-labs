package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@DynamicUpdate
@Table(appliesTo = "nodes")
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "nodes")
    private List<TagEntity> tags;
}