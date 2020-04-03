package com.yvanscoop.apicoordonneesville.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false,length = 200)
    private String name;

    private String countryCode;

    @Embedded
    private BoundingBox boundingBox;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<State> states = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "country_polygonPoints", joinColumns = @JoinColumn(name = "idCountry"))
    private Set<PolygonPoint> polygonPoints = new HashSet<>();
}
