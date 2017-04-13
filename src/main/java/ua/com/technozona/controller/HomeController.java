package ua.com.technozona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Client;
import ua.com.technozona.model.Employee;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.ClientService;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    ClientService clientService;

    @Autowired
    CategoryService categoryService;



    @RequestMapping(value = "/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("shop");
        model.addObject("user","roma");
        model.addObject("categories",categoryService.getAll());
        return model;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView model = new ModelAndView("login");
        model.addObject("clientForm", new Client());
        return model;
    }


    @RequestMapping(value = "/registerClient")
    public String registerClient(@ModelAttribute("clientForm") Client clientForm){
        clientService.add(clientForm);
        return "redirect:/login";
    }

//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logout (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }

    private String getPrincipal(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Employee){
//            new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED);
        } else if (user instanceof Client){

        }
        return null;
    }

}
