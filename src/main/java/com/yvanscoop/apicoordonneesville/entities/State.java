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
public class State implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @OneToMany(targetEntity = City.class,cascade = CascadeType.ALL, mappedBy = "state", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<City> cities = new HashSet<>();

    @Embedded
    private BoundingBox boundingBox;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_country")
    private Country country;
}
