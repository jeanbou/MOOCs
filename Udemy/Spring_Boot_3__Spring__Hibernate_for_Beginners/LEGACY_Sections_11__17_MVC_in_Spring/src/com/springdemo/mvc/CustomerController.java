package com.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    // add initiBinder to trim the string of input
    @InitBinder
    public void initBinder(WebDataBinder wdb) {
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        wdb.registerCustomEditor(String.class, ste);
    }
    
    @RequestMapping("/showForm")
    public String showForm(Model m) {
        m.addAttribute("customer",new Customer());
        return "cust-form";
    }
    
    // Process the form
    @RequestMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer aCustomer,
            BindingResult aBuildingRes) {
        
        System.out.println("DEBUG OF BINDING "+aBuildingRes.toString());
        
        if (aBuildingRes.hasErrors()) {
            return "cust-form";
        }
        return "cust-conf";
    }

}
