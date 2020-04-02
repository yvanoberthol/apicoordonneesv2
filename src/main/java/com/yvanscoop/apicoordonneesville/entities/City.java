package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class City implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long osm_id;

    @Column(unique = true)
    private Long place_id;

    @Column(unique = true)
    private String name;
    private String county;

    @Embedded
    private Coordinate coordinate;

    private String display_name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idState")
    private State state;

    @Embedded
    private BoundingBox boundingBox;

    @ElementCollection
    @CollectionTable(name = "city_polygonPoints", joinColumns = @JoinColumn(name = "idCity"))
    private Set<PolygonPoint> polygonPoints = new HashSet<>();

}

