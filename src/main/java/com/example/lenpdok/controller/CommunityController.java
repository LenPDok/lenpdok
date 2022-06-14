package com.example.lenpdok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/community")
@Controller
public class CommunityController {
    @GetMapping("/board")
    public ModelAndView community() {
        ModelAndView modelAndView = new ModelAndView("/community/list");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView communityDetail(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("/community/view");
        return modelAndView;
    }

    @GetMapping("/write")
    public ModelAndView writeCommunity() {
        ModelAndView modelAndView = new ModelAndView("/community/write");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView writeCommunity(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("/community/edit");
        return modelAndView;
    }
}
