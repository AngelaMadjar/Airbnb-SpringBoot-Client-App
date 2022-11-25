package com.angelamadjar.airbnbproject.web.controllers;


import com.angelamadjar.airbnbproject.model.PricePredictionDTO;
import com.angelamadjar.airbnbproject.model.enumerations.ListingObjectType;
import com.angelamadjar.airbnbproject.model.enumerations.Months;
import com.angelamadjar.airbnbproject.model.enumerations.RoomTypeCategory;
import com.angelamadjar.airbnbproject.service.interfaces.AccommodationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value="predictor")
public class PricePredictorController {

    private final AccommodationService accommodationService;

    public PricePredictorController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }


    /**
     * GET FUNCTIONS
     * **/
    @GetMapping
    public String getPricePredictorPage(@RequestParam(required = false)String error, Model model){

        //  if there is some error, put it into the model in order to display it on frontend
        if(error !=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        this.addToModel(model);
        return "price-estimator-form";
    }



    /**
     * POST FUNCTIONS
     * **/
    @PostMapping("/predict")
    public String calculatePrice(Model model,
                                @RequestParam String city,
                                @RequestParam Double avgRating,
                                @RequestParam Integer reviewsCount,
                                @RequestParam String listingObjType,
                                @RequestParam String spaceType,
                                @RequestParam String roomTypeCategory,
                                @RequestParam String listingGuestLabel,
                                @RequestParam Double bathrooms,
                                @RequestParam Integer bedrooms,
                                @RequestParam Integer beds,
                                @RequestParam String checkIn,
                                @RequestParam Boolean isSuperHost
                                 ){


        PricePredictionDTO pricePredictionDTO = new PricePredictionDTO(city,avgRating,reviewsCount,
                                                                listingObjType,spaceType,roomTypeCategory
                                                                ,listingGuestLabel,bathrooms,bedrooms,beds,checkIn,isSuperHost);
        Double prediction = this.accommodationService.getPredictedPrice(pricePredictionDTO);

        model.addAttribute("prediction",prediction);
        return "price-estimator-form";
    }




    public void addToModel(Model model){
        // get the all the possible values for the enum types
        List<ListingObjectType> listingObjectTypes = List.of(ListingObjectType.values());
        List<RoomTypeCategory> roomTypeCategories = List.of(RoomTypeCategory.values());
        List<Months> months = List.of(Months.values());

        // Note: I did not create enum types for the rest of the attributes because they contain empty spaces and special characters
        // Instead, I added them to different lists that I add to the model
        // List of spaceType values
        List<String> spaceTypeLists = new ArrayList<>();
        spaceTypeLists.add("Apartment");
        spaceTypeLists.add("Loft");
        spaceTypeLists.add("Private room");
        spaceTypeLists.add("Condo");
        spaceTypeLists.add("Tiny home");
        spaceTypeLists.add("Vacation home");
        spaceTypeLists.add("Shared room");
        spaceTypeLists.add("Hotel room");
        spaceTypeLists.add("Cabin");
        spaceTypeLists.add("Trullo");
        spaceTypeLists.add("Home");
        spaceTypeLists.add("Dome");
        spaceTypeLists.add("Guest suite");
        spaceTypeLists.add("Farm stay");
        spaceTypeLists.add("Villa");
        spaceTypeLists.add("Townhouse");

        // List of city values
        List<String> cities = new ArrayList<>();
        cities.add("Rome");
        cities.add("Florence");
        cities.add("Milan");
        cities.add("Venice");
        cities.add("Genoa");
        cities.add("L'Aquila");
        cities.add("Bari");
        cities.add("Ostuni");
        cities.add("Matera");
        cities.add("Alberobello");
        cities.add("Brindisi");
        cities.add("Lecce");
        cities.add("Ancona");
        cities.add("Catania");
        cities.add("Bologna");
        cities.add("Amalfi");

        // List of boolean values
        List<Boolean> isSuperHostList = new ArrayList<>();
        isSuperHostList.add(true);
        isSuperHostList.add(false);

        // this data is added to the model so that it can be displayed in the view layer
        model.addAttribute("cities", cities);
        model.addAttribute("isSuperHosts", isSuperHostList);
        model.addAttribute("months", months);
        model.addAttribute("spaceTypeLists", spaceTypeLists);
        model.addAttribute("listingObjectTypes", listingObjectTypes);
        model.addAttribute("roomTypeCategories", roomTypeCategories);
    }
}
