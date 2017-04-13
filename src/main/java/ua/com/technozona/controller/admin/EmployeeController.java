package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Category;
import ua.com.technozona.model.Employee;
import ua.com.technozona.service.interfaces.*;

import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = {"/",""})
    public ModelAndView view(){
        ModelAndView model = new ModelAndView("admin/employees");
        model.addObject("admin",getAdmin());
        return model;
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @RequestMapping(value = {"/getAllAdmins"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Employee> getAllAdmins(){
        return employeeService.getAdministrators();
    }

    @RequestMapping(value = {"/getAllManagers"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Employee> getAllManagers(){
        return employeeService.getManagers();
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee){
        employeeService.add(employee);
        return employee;
    }


    @RequestMapping(value = {"/get/{id}"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee getEMployee(@PathVariable("id") Long id){
        return employeeService.get(id);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeService.remove(id);
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.update(employee);
        return employee;
    }


    private Employee getAdmin(){
        return  (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
