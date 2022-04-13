package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationEntity {

    @Column
    private MemberList member;

    @Column
    private TagList tag;

    @Id
    @Column(nullable = false)
    private Integer id;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Member implements Serializable {
        private String type;
        private BigInteger ref;
        private String role;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberList implements Serializable {
        private List<Member> members;
    }
}