package com.yvanscoop.apicoordonneesville.request;

import com.yvanscoop.apicoordonneesville.entities.BoundingBox;
import com.yvanscoop.apicoordonneesville.entities.PolygonPoint;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class AddStateRequestBody {
    private Long id;
    private String name;
    private String countryName;
    private BoundingBox boundingBox;
    private Set<PolygonPoint> polygonPoints = new HashSet<>();
}
