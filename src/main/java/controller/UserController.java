package controller;

import com.example.e_commerce.app.entity.User;
import com.example.e_commerce.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/add/user")
    public String addUser(){
        return "AddUser";
    }
    @PostMapping(path = "/add/user")
    public String addUser(User user){
        userService.createUser(user);

        return "Login";
    }

    @GetMapping("/update/user/{id}")
    public String updateUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));

        return "UpdateUser";
    }

    @PostMapping(path = "/update/user")
    public String updateUser(User user){
        userService.updateUser(user);
        return "/admin/home";
    }
    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "/admin/home";
    }
}
