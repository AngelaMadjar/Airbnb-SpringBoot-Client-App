package com.angelamadjar.airbnbproject.service.impl;

import com.angelamadjar.airbnbproject.exception.InvalidIdException;
import com.angelamadjar.airbnbproject.model.Accommodation;
import com.angelamadjar.airbnbproject.model.PricePredictionDTO;
import com.angelamadjar.airbnbproject.model.enumerations.ListingObjectType;
import com.angelamadjar.airbnbproject.model.enumerations.RoomTypeCategory;
import com.angelamadjar.airbnbproject.repository.AccommodationRepository;
import com.angelamadjar.airbnbproject.service.interfaces.AccommodationService;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

// The Service layer contains all the business logic of the application
// Implements the AccommodationService interface where all of the functions are defined

@Service
public class AccommodationServiceImpl implements AccommodationService {


    // creating an instance of the Accommodation repository that enables using "NoCodeRepository" functions
    private final AccommodationRepository accommodationRepository;

    // initializing the repository
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    // list all the accommodations stored in the database
    @Override
    public List<Accommodation> listAll() {
        return this.accommodationRepository.findAll();
    }

    // find an accommodation by its id and throw a custom Exception in case the id does not exist
    @Override
    public Accommodation findById(Long id) {
        return this.accommodationRepository.findById(id).
                orElseThrow(()-> new InvalidIdException(id));
    }

    // filter accommodations by the city they are located in
    @Override
    public List<Accommodation> listByCity(String city) {
        return this.accommodationRepository.findAllByCity(city);
    }

    // create a new city and store it in the database
    @Override
    public Accommodation create(String name, String city,
                                ListingObjectType listingObjType, String spaceType,
                                RoomTypeCategory roomTypeCategory, String listingGuestLabel,
                                Double bathrooms, Integer bedrooms, Integer beds,
                                Boolean isSuperHost, String imageUrl, Double price) {

        // creating an instance
        Accommodation accommodation = new Accommodation(name,city,
                listingObjType,spaceType,roomTypeCategory,listingGuestLabel,
                bathrooms,bedrooms,beds,isSuperHost,imageUrl,price);

        // saving the newly created accommodation in the database
        this.accommodationRepository.save(accommodation);

        // return the newly created accommodation
        return accommodation;
    }

    // edit an existing accommodation by its id
    @Override
    public Accommodation update(Long id, String name, String city,
                                ListingObjectType listingObjType, String spaceType,
                                RoomTypeCategory roomTypeCategory, String listingGuestLabel,
                                Double bathrooms, Integer bedrooms, Integer beds,
                                Boolean isSuperHost, String imageUrl, Double price) {

        // get the accommodation by its id or throw a custom error in case it doesn't exist
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(()-> new InvalidIdException(id));

        // set the new values
        accommodation.setCity(city);
        accommodation.setName(name);
        accommodation.setListingObjType(listingObjType);
        accommodation.setSpaceType(spaceType);
        accommodation.setRoomTypeCategory(roomTypeCategory);
        accommodation.setListingGuestLabel(listingGuestLabel);
        accommodation.setBathrooms(bathrooms);
        accommodation.setBedrooms(bedrooms);
        accommodation.setBeds(beds);
        accommodation.setSuperHost(isSuperHost);
        accommodation.setImageUrl(imageUrl);
        accommodation.setPrice(price);

        // save the edited accommodation in the database
        this.accommodationRepository.save(accommodation);

        // return the edited accommodation
        return accommodation;
    }

    // delete an accommodation by its id
    @Override
    public Accommodation deleteById(Long id) {

        // find the accommodation by specifying its id or throw a custom error in case it doesn't exist
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(()-> new InvalidIdException(id));

        // delete the accommodation
        this.accommodationRepository.delete(accommodation);

        // return the 'deleted' accommodation
        return accommodation;

    }

    // invoke the Price Predictor microservice
    // pass a Data Transfer Object containing the necessary informtion
    @Override
    public Double getPredictedPrice(PricePredictionDTO pricePredictionDTO) {

        // the url of the REST API where the ML model is exposed
        String url = "http://127.0.0.1:5000/predict";

        // RestTemplate is a java class used for consuming RESTful Web Services
        RestTemplate restTemplate = new RestTemplate();

        // JSONObject is an unordered collection of name/value pairs
        JSONObject request = new JSONObject();

        // adding the attributes required by the ML model so that it can make a price prediction
        request.put("city", pricePredictionDTO.getCity());
        request.put("avg_rating", pricePredictionDTO.getAvgRating());
        request.put("reviews_count", pricePredictionDTO.getReviewsCount());
        request.put("listing_obj_type", pricePredictionDTO.getListingObjType());
        request.put("space_type", pricePredictionDTO.getSpaceType());
        request.put("room_type_category", pricePredictionDTO.getRoomTypeCategory());
        request.put("listing_guest_label", pricePredictionDTO.getListingGuestLabel());
        request.put("bathrooms", pricePredictionDTO.getBathrooms());
        request.put("bedrooms", pricePredictionDTO.getBedrooms());
        request.put("beds", pricePredictionDTO.getBeds());
        request.put("check_in", pricePredictionDTO.getCheckIn());
        request.put("is_super_host", pricePredictionDTO.getSuperHost());


        // Json objects should have a declared header that contains information about the type of returned (passed) data
        HttpHeaders headers = new HttpHeaders();

        // setting the type to JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // wrapping the request
        // HTTP request (or response in other cases) entity, consisting of body (the request) and headers
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        // consuming the API's response with the exchange function (POST method)
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        try {
            // Checking if the API returned a valid response
            if (result.getStatusCode() == HttpStatus.OK) {
                // printing out the response in the console
                System.out.println("Result body: " + result.getBody());

                // extracting only the price from the received string
                String res = Objects.requireNonNull(result.getBody()).split(":")[1].replace("}", "");

                // printing out the price
                System.out.println("Predicted price is : " + res);

                // returning the price of type Double
                return Double.parseDouble(res);
            }
        }
        catch (Exception e){
            // throwing a RuntimeException in case something goes wrong
            throw new RuntimeException(e.getMessage());
        }

    return 0.0;

    }
}
