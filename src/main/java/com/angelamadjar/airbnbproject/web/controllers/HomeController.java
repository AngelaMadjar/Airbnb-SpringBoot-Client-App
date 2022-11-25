package com.angelamadjar.airbnbproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controllers control the way users interact with the MVC app.
// It determines what response should be displayed when a user makes a request.

@Controller
@RequestMapping(value={"/","/home"})
public class HomeController {

    // When the user navigates on the Home tab in the header, the getHomePage function is envoked and the index.html is displayed
    @GetMapping
    public String getHomePage(@RequestParam(required = false)String error, Model model){

        //  if there is some error, put it into the model in order to display it on frontend
        if(error !=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "index";
    }
}
