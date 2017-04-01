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

import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin")
public class ProductController {


    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = {"/products"})
    public ModelAndView view(){
        ModelAndView model = new ModelAndView("admin/products");
        model.addObject("user",getPrincipal());
        model.addObject("categories",categoryService.getAll());
        model.addObject("products",productService.getAll());
        return model;
    }

    @RequestMapping(value = {"/products/add"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> addCategory(@RequestBody Category category){
        categoryService.add(category);
        return categoryService.getAll();
    }

    @RequestMapping(value = {"/products/delete/{id}"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> deleteCategory(@PathVariable("id") Long id){
        categoryService.remove(id);
        return categoryService.getAll();
    }

    @RequestMapping(value = {"/products/update/{id}"}, method = RequestMethod.POST,
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
