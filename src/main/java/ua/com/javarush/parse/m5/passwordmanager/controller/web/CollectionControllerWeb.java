package ua.com.javarush.parse.m5.passwordmanager.controller.web;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/collections")
public class CollectionControllerWeb {

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("collection", new CollectionForm());
        return "create-collection";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("collection") CollectionForm form) {
        log.info("[Collections] Create requested: name='{}', color='{}'", form.getName(), form.getColor());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // Placeholder: in a real impl, fetch by id
        CollectionForm existing = new CollectionForm();
        existing.setId(id);
        existing.setName("Sample Collection");
        existing.setDescription("Edit this collection");
        existing.setColor("#1f2937");
        model.addAttribute("collection", existing);
        return "edit-collection";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("collection") CollectionForm form) {
        log.info("[Collections] Update requested for id={}, name='{}'", id, form.getName());
        return "redirect:/";
    }

    @Data
    public static class CollectionForm {
        private Long id;
        private String name;
        private String description;
        private String color;
    }
}


