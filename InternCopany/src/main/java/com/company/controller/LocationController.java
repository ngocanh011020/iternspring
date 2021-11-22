package com.company.controller;

import com.company.exception.EmployeeNotFoundException;
import com.company.dto.Location;
import com.company.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping("/locations")
    public String showLocationList(Model model) {
        return viewPage(model,1);
    }
    @RequestMapping("/location/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum) {


        Page<Location> page = service.listAll(pageNum);
        List<Location> listLocations = page.getContent();
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("listLocation", listLocations);
        return "locations";
    }

    @GetMapping("/location/new")
    public String showNewForm(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("pageTitle", "Add New Location");
        return "location_form";
    }

    @PostMapping("/location/save")
    public String saveLocation(Location location, RedirectAttributes ra) {
        service.save(location);
        ra.addFlashAttribute("message", "The Location has been saved successfully");
        return "redirect:/locations";
    }

    @GetMapping("/location/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Location location = service.get(id);
            model.addAttribute("location", location);
            model.addAttribute("pageTitle", "Edit Location (ID: " + id +")");
            return "location_form";
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/locations";
        }
    }

    @GetMapping("/location/delete/{id}")
    public String deleteLocation(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Location ID "+ id + " has been deleted");
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/locations";
    }
}

