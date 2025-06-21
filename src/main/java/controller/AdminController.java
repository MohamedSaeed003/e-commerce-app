package controller;

import com.example.e_commerce.app.entity.Admin;
import com.example.e_commerce.app.entity.Order;
import com.example.e_commerce.app.entity.Product;
import com.example.e_commerce.app.entity.User;
import com.example.e_commerce.app.service.AdminService;
import com.example.e_commerce.app.service.OrderService;
import com.example.e_commerce.app.service.ProductService;
import com.example.e_commerce.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    private User user;

    @GetMapping("/verifyCredentials")
    public String verifyCredentials(@ModelAttribute("admin") Admin admin, Model model){
        if(adminService.verifyCredentials(admin.getEmail(), admin.getPassword())){
            return "";
        }
        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    @GetMapping("/admin/home")
    public String AdminHomePage(Model model){
        model.addAttribute("adminList", adminService.getAllAdmin());
        model.addAttribute("userList", userService.getAllUser());
        model.addAttribute("orderList", orderService.getAllOrder());
        model.addAttribute("productList", productService.getAllProduct());

        return"AdminHomePage";
    }
    @GetMapping("/add/admin")
    public String createAdmin(){
        return "AddAdmin";
    }

    @PostMapping
    public String createAdmin(Admin admin){
        adminService.createAdmin(admin);
        return "/admin/home";
    }

    @GetMapping("/update/admin/{id}")
    public String updateAdmin(@PathVariable Long id, Model model){
        model.addAttribute("admin", adminService.getAdminById(id));

        return "UpdateAdmin";
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return "/admin/home";
    }

    @DeleteMapping("/delete/admin/{id}")
    public String deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);

        return"/admin/home";
    }

    @GetMapping("/user/login")
    public String userLogin(User user, Model model) {
        if (userService.verifyCredentials(user.getEmail(), user.getPassword())) {
            user = userService.findUserByEmail(user.getEmail());
            model.addAttribute("ordersList", orderService.findOrdersByUserId(user));
            return "ProductPage";
        }
        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    @GetMapping("/product/search")
    public String productSearch(String name, Model model){
        Product product = productService.findProductByName(name);
        if(product != null){
            model.addAttribute("ordersList", orderService.findOrdersByUserId(user));
            model.addAttribute("product", product);

            return "ProductPage";

        }
        model.addAttribute("error", "Sorry, Product is not found");
        model.addAttribute("ordersList", orderService.findOrdersByUserId(user));
        return "ProductPage";
    }


    @GetMapping("/place/order")
    public String placeOrder(Order order, Model model){
        double totalAmount = order.getPrice()*order.getQuantity();
        order.setAmount(totalAmount);
        order.setUser(user);
        order.setDate(new Date());

        orderService.createOrder(order);
        model.addAttribute("amount", totalAmount);
        return "OrderStatus";
    }


}
