package com.angelamadjar.airbnbproject.web.controllers;

import com.angelamadjar.airbnbproject.model.Accommodation;
import com.angelamadjar.airbnbproject.model.enumerations.ListingObjectType;
import com.angelamadjar.airbnbproject.model.enumerations.Months;
import com.angelamadjar.airbnbproject.model.enumerations.RoomTypeCategory;
import com.angelamadjar.airbnbproject.service.interfaces.AccommodationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="accommodations")
public class AccommodationsController {

    // creating an instance of accommodationService, so that I can use its functions in the controller
    private final AccommodationService accommodationService;

    // initializing it in a constructor
    public AccommodationsController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }


    /**
     * GET FUNCTIONS
     * **/
    @GetMapping
    public String getAccommodationsPage(@RequestParam(required = false) String error, Model model){

        //  if there is some error, put it into the model in order to display it on frontend
        if(error !=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }

        // get all the accommodations by invoking the listAll method from the accommodation Service
        // which actually uses the accommodationRepository to get the accommodations from the database
        List<Accommodation> accommodationList = this.accommodationService.listAll();

        // add the list of obtained accommodations in the model so that they can be displayed on the frontend
        model.addAttribute("accommodations",accommodationList);

        // return the accommodations.html template
        return "accommodations";
    }

    // edit function
    @GetMapping("/edit-form/{id}")
    public String editAccommodationPage(@PathVariable Long id, Model model){
        // if an accommodation with such id exists
        if(this.accommodationService.findById(id) != null){
            // get the accommodation with the specified id
            Accommodation accommodation = this.accommodationService.findById(id);

            this.addToModel(model);

            // add the accommodation to the model so that it can be accessed in the view layer
            model.addAttribute("accommodation", accommodation);

            // return the add-form template
            return "add-form";
        }
        // if the id does not exists, redirect to an error page
        return "redirect:/accommodations?error=AccommodationNotFound";
    }


    @GetMapping("/add-form")
    public String addAccommodationPage(Model model){
        this.addToModel(model);
            return "add-form";
    }



    /**
     * POST FUNCTIONS
     * **/
    @PostMapping("/add")
    public String saveAccommodation(   @RequestParam(required = false) Long accommodationId, // the id is not required so that this function has two purposes
                                       @RequestParam String name, // the rest of the parameters are required
                                       @RequestParam String city,
                                       @RequestParam ListingObjectType listingObjType,
                                       @RequestParam String spaceType,
                                       @RequestParam RoomTypeCategory roomTypeCategory,
                                       @RequestParam String listingGuestLabel,
                                       @RequestParam Double bathrooms,
                                       @RequestParam Integer bedrooms,
                                       @RequestParam Integer beds,
                                       @RequestParam Boolean isSuperHost,
                                       @RequestParam String imageUrl,
                                       @RequestParam Double price)
    {
        // if the id is specified, edit the accommodation with the specified parameters
        if (accommodationId != null) {
            // the error handling in case of an id that doesn't exist is done in the service layer
            this.accommodationService.update(accommodationId, name,city,
                     listingObjType, spaceType,
                     roomTypeCategory, listingGuestLabel,
                     bathrooms, bedrooms, beds,
                     isSuperHost, imageUrl, price);
        } else{
            // if the id is not specified, create a new accommodation with the specified parameters
            this.accommodationService.create(name,city,
                     listingObjType, spaceType,
                     roomTypeCategory, listingGuestLabel,
                     bathrooms, bedrooms, beds,
                     isSuperHost, imageUrl, price);
        }

        // redirect to the template where all of the accommodations are listed to view the changes
        return "redirect:/accommodations";
    }


    /**
     * DELETE FUNCTIONS
     * **/
    @DeleteMapping("/delete/{id}")
    public String deleteAccommodation(@PathVariable Long id){
        // delete the accommodation with the specified id
        this.accommodationService.deleteById(id);

        // redirect to the template where all of the accommodations are listed to view the changes
        return "redirect:/accommodations";
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
