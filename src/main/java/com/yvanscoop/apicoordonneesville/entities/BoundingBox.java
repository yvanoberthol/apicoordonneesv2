package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class BoundingBox implements Serializable {
    private Double southLatitude;
    private Double northLatitude;
    private Double westLongitude;
    private Double eastLongitude;
}
