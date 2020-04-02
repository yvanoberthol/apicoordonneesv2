package com.yvanscoop.apicoordonneesville.request;

import com.yvanscoop.apicoordonneesville.entities.BoundingBox;
import com.yvanscoop.apicoordonneesville.entities.Coordinate;
import com.yvanscoop.apicoordonneesville.entities.PolygonPoint;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class AddCityRequestBody {
    private Long id;
    private Long osm_id;
    private Long place_id;

    private String name;
    private String county;

    private Coordinate coordinate;

    private String display_name;

    private String stateName;

    private BoundingBox boundingBox;

    private Set<PolygonPoint> polygonPoints = new HashSet<>();
}
