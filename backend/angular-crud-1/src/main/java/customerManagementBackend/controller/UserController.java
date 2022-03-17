package customerManagementBackend.controller;


import customerManagementBackend.entity.User;
import customerManagementBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/api/user")
@ResponseBody
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/signUp/{name}/{email}/{password}")
    public String signUser(@PathVariable("name")String name, @PathVariable("email")String email, @PathVariable("password")String password){
          userService.save(new User(name,email,password));
        return "sign up success!";
    }
    @RequestMapping("/getUser/{email}")
    public User getUser(@PathVariable("email")String email){
        User userToReturn=userService.getUser(email);
        return userToReturn;
    }
    @RequestMapping("/loginUser/{email}")
    public User loginUser(@PathVariable("email")String email){
        User userToReturn=userService.getUser(email);
        return userToReturn;
    }

}
