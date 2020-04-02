package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Coordinate {
    private float latitude;
    private float longitude;
}
