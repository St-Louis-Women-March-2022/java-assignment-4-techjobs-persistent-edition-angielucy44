package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;


    //Added index method to return a list of all of the skills in the database
        @GetMapping("")
        public String index (Model model) {
           model.addAttribute("title", "All Skills");
           //List skills = (List<Skill>) skillRepository.findAll();
            model.addAttribute("skills", skillRepository.findAll());
            return "skills/index";
        }


    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
            model.addAttribute(new Skill());
            return "skills/add";
    }

    //this method is used so that if there are any errors or invalid entries into the fields
    //it sends the user back to the form to try again
    //added code to save the object if it is valid
    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {
            if (errors.hasErrors()) {
                return "skills/add";
            }
            skillRepository.save(newSkill);
            return "redirect:./";
    }

    //this method is used to view an individual skill object
    //uses skill object's id field to get the correct info
    //from SkillRepository
    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

            Optional<Skill> optSkill = skillRepository.findById(skillId);
            if (optSkill.isPresent()) {
                Skill skill = (Skill) optSkill.get();
                model.addAttribute("skill", skill);
                return "skills/view";
            } else {
                return "redirect:../";
            }

    }
}
