package com.redsocial.redsocialpro.controller;

import com.redsocial.redsocialpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search")
    public String searchPage(@RequestParam(required = false) String q, Model model) {
        if (q != null && !q.isEmpty()) {
            model.addAttribute("users", userRepository.searchUsers(q));
            model.addAttribute("searchTerm", q);
        }
        return "search";
    }

    @GetMapping("/profile/{id}")
    public String profilePage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow());
        return "profile";
    }
}