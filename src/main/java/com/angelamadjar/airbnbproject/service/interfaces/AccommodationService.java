package com.angelamadjar.airbnbproject.service.interfaces;

import com.angelamadjar.airbnbproject.model.Accommodation;
import com.angelamadjar.airbnbproject.model.PricePredictionDTO;
import com.angelamadjar.airbnbproject.model.enumerations.ListingObjectType;
import com.angelamadjar.airbnbproject.model.enumerations.RoomTypeCategory;

import java.util.List;

// I created an interface to achieve a higher level of abstraction

public interface AccommodationService {

    // Returns all the accommodations stored in the database
    List<Accommodation> listAll();

    // Returns an accommodation acquired by its id
    Accommodation findById(Long id);

    // Create a new accommodation
    Accommodation create(String name, String city,
                         ListingObjectType listingObjType, String spaceType,
                         RoomTypeCategory roomTypeCategory, String listingGuestLabel,
                         Double bathrooms, Integer bedrooms, Integer beds,
                         Boolean isSuperHost, String imageUrl, Double price);

    // Edit an existing accommodation by specifying its id
    Accommodation update(Long id, String name, String city,
                         ListingObjectType listingObjType, String spaceType,
                         RoomTypeCategory roomTypeCategory, String listingGuestLabel,
                         Double bathrooms, Integer bedrooms, Integer beds,
                         Boolean isSuperHost, String imageUrl, Double price);

    // Delete an accommodation by specifying its id
    Accommodation deleteById(Long id);

    // Get the predicted price by providing a Data Transfer Object to the Price Predictor microservice
    Double getPredictedPrice(PricePredictionDTO pricePredictionDTO);

    // TODO:
    // Implement a function for listing the accommodations by the city they are located in
    List<Accommodation> listByCity(String city);
}
