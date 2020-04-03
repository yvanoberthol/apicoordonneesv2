package com.yvanscoop.apicoordonneesville.repositories;

import com.yvanscoop.apicoordonneesville.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "states", path = "states")
public interface StateRepository extends JpaRepository<State,Long> {

    State findByName(String name);

}
