package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping("eventCategories")
    public String displayAllCategories(Model model) {
        model.addAttribute("title","All Categories");
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("eventCategories/create")
    public String renderCreateEventCategoryForm(Model model) {
        model.addAttribute("title","Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("eventCategories/create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Create Category");
                    return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:/eventCategories";
    }
}
