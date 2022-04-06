package ru.nsu.ccfit.beloglazov.dis.dis3.entities;

import java.io.Serializable;
import java.util.Objects;

public class TagId implements Serializable {

    private Integer nodeId;

    private String k;

    public TagId(Integer nodeId, String k) {
        this.nodeId = nodeId;
        this.k = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagId tagId = (TagId) o;
        return Objects.equals(nodeId, tagId.nodeId) && Objects.equals(k, tagId.k);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, k);
    }
}