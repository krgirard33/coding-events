package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    // lives at events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title","Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("types",EventType.values());
        return "events/create";
    }

    // lives at events/create
    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title","Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId, @ModelAttribute Event newEvent) {
        Event eventToEdit = EventData.getById(eventId);
        System.out.println("************************************ "+eventToEdit.getId()+" **********************************");
        model.addAttribute("eventId", eventId);
        String title = "Edit Event "+eventToEdit.getName()+" (id="+eventToEdit.getId()+")";
        model.addAttribute("title",title);
        model.addAttribute("types",EventType.values());
        model.addAttribute("event", eventToEdit);
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description, String contactEmail, EventType type) {
//        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        System.out.println("caw caw caw "+eventId+" "+name+" "+type+" caw caw caw");

      Event eventToEdit = EventData.getById(eventId);
//        System.out.println("``````````````````````````````"+eventToEdit.getId()+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        eventToEdit.setContactEmail(contactEmail);
//        eventToEdit.setType(type);
        return "redirect:";
    }
}
