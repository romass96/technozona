package ua.com.technozona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.enums.RoleEnum;
import ua.com.technozona.model.Role;
import ua.com.technozona.model.User;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.RoleService;
import ua.com.technozona.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("main");
        model.addObject("user",getPrincipal());
        model.addObject("categories",categoryService.getAll());
        return model;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView model = new ModelAndView("login");
        model.addObject("userForm", new User());
        return model;
    }


    @RequestMapping(value = "/registerClient")
    public String registerClient(@ModelAttribute("userForm") User userForm){
        userForm.setRole(roleService.getDefault());
        userService.add(userForm);
        System.out.println(userForm);
        return "redirect:/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    private String getPrincipal(){
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getName();
    }

}
