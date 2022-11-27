package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {


    @Autowired
    private EmployerRepository employerRepository;


    //Added index method to return a list of all of the employers in the database
    @GetMapping("")
    public String index (Model model) {
        model.addAttribute("title", "All Employers");
        List employers = (List<Employer>) employerRepository.findAll();
        model.addAttribute("employers", employers);
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    //this method is used so that if there are any errors or invalid entries into the fields
    //it sends the user back to the form to try again
    //added code to save the object if it is valid
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Employer");
            return "employers/add";
        }
        employerRepository.save(newEmployer);
        return "redirect:";
    }

    //this method is used to view an individual employer object
    //uses employer object's id field to get the correct info
    //from EmployerRepository
    //optEmployer was set to null. Replaced that with findById method
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
