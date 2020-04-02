package com.yvanscoop.apicoordonneesville.controllers;

import com.yvanscoop.apicoordonneesville.entities.Country;
import com.yvanscoop.apicoordonneesville.entities.State;
import com.yvanscoop.apicoordonneesville.repositories.CountryRepository;
import com.yvanscoop.apicoordonneesville.repositories.StateRepository;
import com.yvanscoop.apicoordonneesville.request.AddStateRequestBody;
import com.yvanscoop.apicoordonneesville.response.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;


    @PostMapping(value = "/states/add")
    public ResponseEntity<Response> addCity(@RequestBody AddStateRequestBody stateRequestBody){
        Response response = new Response();
        Country countryByName = countryRepository.findByName(stateRequestBody.getCountryName());
        if (countryByName == null){
            response.msg = "No country registred with this name";
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        ModelMapper modelMapper = new ModelMapper();
        State state = modelMapper.map(stateRequestBody, State.class);
        state.setCountry(countryByName);

        stateRepository.save(state);
        response.msg = "State created with success";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
