package com.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hw")
public class HWController {
    
    // Showing initial form
    @RequestMapping("/showForm")
    public String showForm() {
        return "hw-form";
    }
    
    // Process the form
    @RequestMapping("/processForm")
    public String processForm() {
        return "hw-name";
    }
    
 // Process the form
    @RequestMapping("/processForm2")
    public String processForm2(@RequestParam("stName") String studentName, Model m) {
        // rq HttpServletRequest
        //m.addAttribute("message", " Yo " + rq.getParameter("stName").toUpperCase());
        m.addAttribute("message","Yo with RequestParam " + studentName.toUpperCase());
        return "hw-name";
    }
}
