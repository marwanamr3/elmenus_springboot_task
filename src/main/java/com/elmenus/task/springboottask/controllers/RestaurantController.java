package com.elmenus.task.springboottask.controllers;

import com.elmenus.task.springboottask.models.Restaurant;
import com.elmenus.task.springboottask.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class RestaurantController {

    RestaurantRepository restRepo = new RestaurantRepository();

    @RequestMapping(value = "/api/restaurant", method = RequestMethod.GET)
    public ResponseEntity<Object> getRestaurant() {
        ArrayList<Restaurant> restaurants = restRepo.getRestaurants();
        if(restaurants == null)
            return new ResponseEntity<>("No restaurants found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(restRepo.getRestaurants(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/restaurant", params = "closed", method = RequestMethod.GET)
    public ResponseEntity<Object> getOpenRestaurants(@RequestParam("closed") boolean closed) {
        ArrayList<Restaurant> openRestaurants = restRepo.getOpenRestaurants(closed);
        if(openRestaurants == null)
            return new ResponseEntity<>("No restaurants found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(restRepo.getOpenRestaurants(closed), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/restaurant", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewRestaurant(@RequestBody @Valid Restaurant restaurant) {
//        if (restaurant == null)
            System.out.println("Hello From");
            System.out.println(restaurant.toString());
        if (restRepo.addNewRestaurant(restaurant))
            return new ResponseEntity<>("Restaurant added successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Restaurant creation failed", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/api/restaurant/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRestaurant(@PathVariable("uuid") String uuid, @RequestBody @Valid Restaurant restaurant) {
        if(restRepo.updateRestaurant(uuid,restaurant))
            return new ResponseEntity<>("Restaurant updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Restaurant update failed", HttpStatus.NOT_ACCEPTABLE);
    }
}
