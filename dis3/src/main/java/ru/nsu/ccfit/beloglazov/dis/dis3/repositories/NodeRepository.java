package ru.nsu.ccfit.beloglazov.dis.dis3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.NodeEntity;
import java.util.List;

public interface NodeRepository extends JpaRepository<NodeEntity, Integer> {

    @Query(
            value = "SELECT * FROM nodes " +
            "WHERE earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(nodes.lat, nodes.lon)) < :rad " +
            "ORDER BY earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(nodes.lat, nodes.lon)) ASC",
            nativeQuery = true
    )
    List<NodeEntity> getNodesInRangeOrderByDistanceAsc(
            @Param("lat") Double lat,
            @Param("lon") Double lon,
            @Param("rad") Double rad
    );
}