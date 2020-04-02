package com.yvanscoop.apicoordonneesville.request;

import com.yvanscoop.apicoordonneesville.entities.BoundingBox;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddStateRequestBody {
    private Long id;
    private String name;
    private String countryName;
    private BoundingBox boundingBox;
}
