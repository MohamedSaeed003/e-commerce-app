package controller;

import com.example.e_commerce.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private ProductService productService;

    @GetMapping({"/","/home"})
    public String homePage(){
        return "HomePage";
    }

    @GetMapping
    public String productPage(Model model){
        model.addAttribute("productList",productService.getAllProduct());
        return  "ProductPage";
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "ContactPage";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "LoginPage";
    }

}
