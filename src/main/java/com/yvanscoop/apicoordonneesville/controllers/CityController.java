package com.yvanscoop.apicoordonneesville.controllers;

import com.yvanscoop.apicoordonneesville.entities.BoundingBox;
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

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<City>> getCityByLatAndLong(@RequestParam(name = "latitude") Float lat, @RequestParam(name = "longitude") Float lng){
        List<City> citiesBounding = new ArrayList<>();
        cityRepository.findAll().forEach(
                city -> {
                    BoundingBox boundingBox = city.getBoundingBox();
                    if ((boundingBox.getNorthLatitude() < lat && boundingBox.getEastLongitude() > lat) &&
                            (boundingBox.getSouthLatitude() < lng && boundingBox.getWestLongitude() > lng)){
                        citiesBounding.add(city);
                    }
                }
        );

        List<City> cities = new ArrayList<>();
        if (citiesBounding.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else if (citiesBounding.size() == 1){
            cities.add(citiesBounding.get(0));
        } else {
            for (City cityOne : citiesBounding) {
                cityOne.getPolygonPoints().forEach(
                        polygonPoint -> {
                            if (polygonPoint.getLat().equals(lat) && polygonPoint.getLng().equals(lng)){
                                cities.add(cityOne);
                            }
                        }
                );
            }
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
