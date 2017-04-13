package ua.com.technozona.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.technozona.model.*;
import ua.com.technozona.service.interfaces.CategoryService;
import ua.com.technozona.service.interfaces.PhotoService;
import ua.com.technozona.service.interfaces.ProductService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@ComponentScan(basePackages = {
        "ua.com.technozona.service"
})
@RequestMapping(value = "/admin/products")
public class ProductController {


    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = {"/",""})
    public ModelAndView view(){
        ModelAndView model = new ModelAndView("admin/products");
        model.addObject("admin",getAdmin());
        model.addObject("categories",categoryService.getAll());
        model.addObject("products",productService.getByCategoryId(3L));
        return model;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product addProduct(@RequestParam("productPhoto") MultipartFile file,
                              @RequestParam("productUrl") String url,
                              @RequestParam("productTitle") String title,
                              @RequestParam("productDescription") String description,
                              @RequestParam("productParameters") String parameters,
                              @RequestParam("productPrice") Long price,
                              @RequestParam("productCategory") Long categoryId
    ) {
        Category category = categoryService.get(categoryId);
        Product product = new Product();
        product.initialize(title,url,parameters,description,category,price);
        productService.add(product);
        Photo photo = new Photo();
        photo.setTitle("Product"+product.getId());
        photo.setPhotoLink(photoService.saveFile(file,photo.getTitle()));
        product.setPhoto(photo);
        productService.update(product);
        return product;
     }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteProduct(@PathVariable("id") Long id){
        productService.remove(id);
    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product updateProduct(@RequestBody Product product){
        productService.update(product);
        return product;
    }


    private Employee getAdmin(){
        return  (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
