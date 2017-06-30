package ua.com.technozona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.exception.EmailExistsException;
import ua.com.technozona.model.*;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.ClientService;
import ua.com.technozona.service.interfaces.ProductService;
import ua.com.technozona.service.interfaces.ShoppingCartService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;
import java.util.Locale;


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

    @Autowired
    ApplicationEventPublisher eventPublisher;



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

    @RequestMapping(value = "/showRegistrationPage", method = RequestMethod.GET)
    public String showRegistrationPage(WebRequest request , Model model)
    {
        ClientDTO clientDTO = new ClientDTO();
        model.addAttribute("client",clientDTO);
        return "registration";
    }


    @RequestMapping(value = "/registerClient")
    public ModelAndView registerClient(
            @ModelAttribute("client") @Valid ClientDTO clientDTO,
            BindingResult result, WebRequest request, Errors errors){
        Client registered = new Client();
        if(!result.hasErrors()){
            registered = createClientAccount(clientDTO, result);
        }
        if (registered == null){
            result.rejectValue("email","message.regError");
        }
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            return new ModelAndView("emailError", "user", clientDTO);
        }
        return new ModelAndView("successRegister", "user", clientDTO);
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        ClientVerificationToken verificationToken = clientService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        Client client = verificationToken.getClient();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale)
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        client.setEnabled(true);
        clientService.saveRegisteredUser(client);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    private Client createClientAccount(ClientDTO accountDto, BindingResult result) {
        Client registered = null;
        try {
            registered = clientService.registerNewClientAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

    private Client getClient(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)&&(authentication!=null)) {
            Client client = (Client) authentication.getPrincipal();
            return client;
        }
        return null;
    }

}
