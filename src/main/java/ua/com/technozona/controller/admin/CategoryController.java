package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Category;
import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.EmployeeService;

import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin/categories")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = {"/",""})
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("admin/categories");
        model.addObject("employee",getEmployee());
        return model;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Category addCategory(@RequestBody Category category){
        categoryService.add(category);
        return categoryService.get(category.getUrl());
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> getAllCategories(){
        return categoryService.getAll();
    }


    @RequestMapping(value = {"/get/{id}"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Category getCategory(@PathVariable("id") Long id){
        return categoryService.get(id);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.remove(id);
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Category updateCategory(@RequestBody Category category){
        categoryService.update(category);
        return category;
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
