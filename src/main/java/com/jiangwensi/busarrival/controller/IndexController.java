package com.jiangwensi.busarrival.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping({"","/","/index"})
    public String index(){
        log.info("index()");
        return "index";
    }
}
