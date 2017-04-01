package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Category;
import ua.com.technozona.model.User;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.ProductService;
import ua.com.technozona.service.interfaces.UserService;

import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin")
public class StaffController {


    @Autowired
    UserService userService;


    @RequestMapping(value = {"/staff"})
    public ModelAndView view(){
        ModelAndView model = new ModelAndView("admin/staff");
        model.addObject("user",getPrincipal());
        model.addObject("admins",userService.getAdministrators());
        model.addObject("managers",userService.getManagers());
        return model;
    }


    private String getPrincipal(){
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getName();
    }

}
