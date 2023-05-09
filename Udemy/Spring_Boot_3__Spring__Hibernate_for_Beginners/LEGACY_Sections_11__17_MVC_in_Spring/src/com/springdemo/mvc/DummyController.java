package com.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DummyController {

    @RequestMapping("/showForm")
    public String displayForm() {
        return "hw-form";
    }
    
    //@RequestMapping("/processForm2")
    //public String processForm2(@RequestParam("stName") String studentName, Model m) {
    //    m.addAttribute("message","Yo with RequestParam " + studentName.toUpperCase());
    //    return "hw-name";
    //}
}
