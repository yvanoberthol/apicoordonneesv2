package com.yvanscoop.apicoordonneesville.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class Address implements Serializable {
    private String city;
    private String county;
    private String state;
    private String country;
    private String countryCode;
}
