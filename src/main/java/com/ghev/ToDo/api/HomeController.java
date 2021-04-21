package com.ghev.ToDo.api;


import com.ghev.ToDo.exception.UsernameAlreadyExist;
import com.ghev.ToDo.model.User;
import com.ghev.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;


    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /*

    Login

     */

    @GetMapping("/login")
    public String getLogin(@ModelAttribute("user") User user){

        return "login";
    }

    /*

    Registration

     */

    @GetMapping("/register")
    public String getRegister(Model model){

    model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("reginstratioForm",user);
            return "register";
        }
        try {
            userService.addUser(user);
        }catch(Exception e){
            bindingResult.rejectValue("username","user.username","Username already exist");
            model.addAttribute("registrationForm",user);
            return "register";
        }
        return "redirect:/";
    }

}
