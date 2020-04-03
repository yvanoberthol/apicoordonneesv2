package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Coordinate {
    @Column(precision = 7)
    private Float latitude;
    @Column(precision = 7)
    private Float longitude;
}
