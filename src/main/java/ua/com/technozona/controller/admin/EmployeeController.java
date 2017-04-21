package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Category;
import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;
import ua.com.technozona.service.interfaces.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin/employees")
public class EmployeeController {

    @Autowired
    AdminService adminService;

    @Autowired
    ManagerService managerService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = {"/admins"})
    public ModelAndView viewAdmins(){
        ModelAndView model = new ModelAndView("admin/admins");
        model.addObject("admin",getAdmin());
        return model;
    }

    @RequestMapping(value = {"/managers"})
    public ModelAndView viewManagers(){
        ModelAndView model = new ModelAndView("admin/managers");
        model.addObject("admin",getAdmin());
        return model;
    }

    @RequestMapping(value = {"/getAllAdmins"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Admin> getAllAdmins(){
        return adminService.getAll();
    }

    @RequestMapping(value = {"/getAllManagers"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Manager> getAllManagers(){
        return managerService.getAll();
    }

    @RequestMapping(value = {"/addAdmin"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Admin addAdmin(@RequestBody Admin admin){
            adminService.add(admin);
            return admin;
    }

    @RequestMapping(value = {"/addManager"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Manager addManager(@RequestBody Manager manager){
        managerService.add(manager);
        return manager;
    }

    @RequestMapping(value = {"/getAdmin/{id}"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Admin getAdmin(@PathVariable("id") Long id){
        return adminService.get(id);
    }

    @RequestMapping(value = {"/getManager/{id}"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Manager getManager(@PathVariable("id") Long id){
        return managerService.get(id);
    }

    @RequestMapping(value = {"/deleteAdmin/{id}"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAdmin(@PathVariable("id") Long id){
        adminService.remove(id);
    }

    @RequestMapping(value = {"/deleteManager/{id}"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteManager(@PathVariable("id") Long id){
        managerService.remove(id);
    }

    @RequestMapping(value = {"/updateAdmin"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Admin updateAdmin(@RequestBody Admin admin){
        adminService.update(admin);
        return admin;
    }

    @RequestMapping(value = {"/updateManager"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Manager updateManager(@RequestBody Manager manager){
        managerService.update(manager);
        return manager;
    }


    private Admin getAdmin(){
        return  (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
