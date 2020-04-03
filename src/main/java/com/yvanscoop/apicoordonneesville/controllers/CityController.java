package com.yvanscoop.apicoordonneesville.controllers;

import com.yvanscoop.apicoordonneesville.entities.City;
import com.yvanscoop.apicoordonneesville.entities.State;
import com.yvanscoop.apicoordonneesville.repositories.CityRepository;
import com.yvanscoop.apicoordonneesville.repositories.StateRepository;
import com.yvanscoop.apicoordonneesville.request.AddCityRequestBody;
import com.yvanscoop.apicoordonneesville.response.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;


    @PostMapping(value = "/cities/add")
    public ResponseEntity<Response> addCity(@RequestBody AddCityRequestBody cityRequestBody){
        Response response = new Response();
        State stateByName = stateRepository.findByName(cityRequestBody.getStateName());
        if (stateByName == null){
            response.msg = "No state registred with this name";
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        ModelMapper modelMapper = new ModelMapper();
        City city = modelMapper.map(cityRequestBody, City.class);
        city.setState(stateByName);

        cityRepository.save(city);
        response.msg = "City created with success";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/city/get")
    public ResponseEntity<City> getCityByLatAndLong(@RequestParam(name = "latitude") Float lat, @RequestParam(name = "longitude") Float lng){
        System.out.println(lat);
        System.out.println(lng);
        City city = cityRepository.findByCoordinateLatitudeAndCoordinateLongitude(lat,lng);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
