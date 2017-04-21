package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Client;
import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;
import ua.com.technozona.service.interfaces.ClientService;
import ua.com.technozona.service.interfaces.EmployeeService;

import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = {"/",""})
    public ModelAndView viewAll(){
        ModelAndView model = new ModelAndView("admin/clients");
        model.addObject("employee",getEmployee());
        return model;
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Client> getAllClients(){
        return clientService.getAll();
    }

    private Employee getEmployee(){
        UserDetails currentEmployee = employeeService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (currentEmployee instanceof Admin){
            return (Admin) currentEmployee;
        } else {
            return (Manager) currentEmployee;
        }
    }

}
