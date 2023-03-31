package com.check.pay.Controller;


import com.check.pay.Entity.User;
import com.check.pay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User added";
    }
    @GetMapping("/get/{userId}")
    public int getTotalSuccessfulTxn(@PathVariable int userId){
        return userService.getTotalSuccessfulTxn(userId);
    }

    @GetMapping("/getUserId")
    public int getUserWithMaxRefund(){
        return userService.getUserWithMaxRefund();

    }

}
