package ua.com.technozona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Client;
import ua.com.technozona.model.Employee;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.ClientService;
import ua.com.technozona.service.interfaces.ProductService;
import ua.com.technozona.service.interfaces.ShoppingCartService;

import java.security.Principal;


@Controller
@ComponentScan(basePackages = {"ua.com.technozona.service"})
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    ClientService clientService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;



    @RequestMapping(value = "/main")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("shop");
        model.addObject("client",getClient());
        model.addObject("categories",categoryService.getAll());
        model.addObject("products",productService.getByCategoryId(1L));
        model.addObject("shoppingCart",shoppingCartService.getShoppingCart());
        return model;
    }

    @RequestMapping(value = "/addSale")
    public ModelAndView addProductToShoppingCart(){
       return null;
    }

    @RequestMapping(value = "/loginAdmin")
    public ModelAndView login()
    {
        ModelAndView model = new ModelAndView("loginAdmin");
        return model;
    }

    @RequestMapping(value = "/showRegistrationPage")
    public ModelAndView showRegistrationPage()
    {
        ModelAndView model = new ModelAndView("registration");
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

    private Client getClient(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)&&(authentication!=null)) {
            Client client = (Client) authentication.getPrincipal();
            return client;
        }
        return null;
    }

}
