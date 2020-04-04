package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class BoundingBox implements Serializable {
    private float southLatitude; //min lng
    private float northLatitude; //min lat
    private float westLongitude; //max lng
    private float eastLongitude; //max lat
}
