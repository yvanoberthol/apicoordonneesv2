package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Location implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private Long osm_id;
    @Column(unique = true)
    private Long place_id;
    private Double latitude;
    private Double longitude;

    private String display_name;

    @Embedded
    private Address address;

    @Embedded
    private BoundingBox boundingBox;

    @ElementCollection
    @CollectionTable(name = "location_polygonPoints", joinColumns = @JoinColumn(name = "idLocation"))
    private List<PolygonPoint> polygonPoints = new ArrayList<>();

}

