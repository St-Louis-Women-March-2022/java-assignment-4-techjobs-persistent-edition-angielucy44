package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //Added all repositories as fields
    //Added Autowired annotations
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;


    //index shows all jobs bc returning index which is html in index template
    //jobs shows all jobs bc of findAll() method
    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        List jobs = (List<Job>) jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "index";
    }

    //Added a GET annotation to get to /add
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());

        List employers = (List<Employer>) employerRepository.findAll();
        model.addAttribute("employers", employers);

        List skills = (List<Skill>) skillRepository.findAll();
        model.addAttribute("skills", skills);
        return "add";
    }

    //@RequestParam annotation used to extract input data
    //(List<Employer>) is in () bc it is casting. Casting means it is using the findAll() method
    //to get the data and make it work in Java. It is telling Java the data type of the data
    //we are expecting.
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            List employers = (List<Employer>) employerRepository.findAll();
            model.addAttribute("employers", employers);
            return "add";
        }
        //Optional<Employer> optEmployer = employerRepository.findById(employerId);
       // if (optEmployer.isPresent()) {
           // Employer employer = optEmployer.get();
            //newJob.setEmployer(employer);

            Employer employer = employerRepository.findById(employerId).orElse(new Employer());
            //if (optEmployer.isPresent()) {
              //  Employer employer = optEmployer.get();
                newJob.setEmployer(employer);
       //}
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        jobRepository.save(newJob);

        return "redirect:./";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:./";
        }
    }
}
