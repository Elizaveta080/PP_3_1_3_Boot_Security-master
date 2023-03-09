package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/edit")
    public String delete(@PathVariable("id") int id){
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/addUser")
    public String newPerson(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}