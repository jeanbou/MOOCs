package com.springdemo.mvc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Value("#{countryOptions}")
    private Map<String, String> countryOptions;
    @RequestMapping("/showForm")
    public String showForm(Model m) {
        Student aStudent = new Student();
        m.addAttribute("student", aStudent);
        m.addAttribute("countryOptions", countryOptions);
        return "std-form";
    }
    
 // Process the form
    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student aStudent) {
        System.out.println("DEBUG : "+aStudent.getFirstName());
        return "student-conf";
    }
}
