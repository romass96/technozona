package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.Category;
import ua.com.technozona.model.User;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.RoleService;
import ua.com.technozona.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = {"/categories","/",""})
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("admin/categories");
        model.addObject("user",getPrincipal());
        model.addObject("categories",categoryService.getAll());
        return model;
    }

    @RequestMapping(value = {"/categories/add"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> addCategory(@RequestBody Category category){
        categoryService.add(category);
        return categoryService.getAll();
    }

    @RequestMapping(value = {"/categories/get/{id}"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Category getCategory(@PathVariable("id") Long id){
        return categoryService.get(id);
    }

    @RequestMapping(value = {"/categories/delete/{id}"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> deleteCategory(@PathVariable("id") Long id){
        categoryService.remove(id);
        return categoryService.getAll();
    }

    @RequestMapping(value = {"/categories/update"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> updateCategory(@RequestBody Category category){
        categoryService.update(category);
        return categoryService.getAll();
    }


    private String getPrincipal(){
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getName();
    }

}
