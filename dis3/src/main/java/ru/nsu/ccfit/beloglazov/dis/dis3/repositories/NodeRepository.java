package ru.nsu.ccfit.beloglazov.dis.dis3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nsu.ccfit.beloglazov.dis.dis3.entities.NodeEntity;
import java.util.List;

public interface NodeRepository extends JpaRepository<NodeEntity, Integer> {

    @Query(
            value = "SELECT * FROM nodes " +
            "WHERE (earth_distance(ll_to_earth(55, 83), ll_to_earth(nodes.lat, nodes.lon)) < :rad + earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(55, 83))) " +
                "AND (earth_distance(ll_to_earth(54, 82), ll_to_earth(nodes.lat, nodes.lon)) < :rad + earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(54, 82))) " +
                "AND earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(nodes.lat, nodes.lon)) < :rad " +
            "ORDER BY earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(nodes.lat, nodes.lon)) ASC",
            nativeQuery = true
    )
    List<NodeEntity> getNodesInRangeOrderByDistanceAsc(
            @Param("lat") Double latitude,
            @Param("lon") Double longtitude,
            @Param("rad") Double radius
    );
}