package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.domain.dto.IconAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Controller
@Slf4j
public class IconAttributeController {
    @GetMapping("/icons")
    public ModelAndView showIconAttributes(){
        ModelAndView modelAndView = new ModelAndView();
        List icons = new ArrayList<>();
        icons.add(new IconAttribute("/images/electric-bus-512.png","<div>Icons made by <a href=\"https://www.flaticon" +
                ".com/authors/icongeek26\" title=\"Icongeek26\">Icongeek26</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a></div>"));
        icons.add(new IconAttribute("/images/error-512.png","Icons made by <a href=\"http://www.freepik.com/\" " +
                "title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\"> www.flaticon.com</a>"));


        modelAndView.addObject("icons",icons);
        modelAndView.setViewName("iconAttribute");
        return modelAndView;
    }
}
