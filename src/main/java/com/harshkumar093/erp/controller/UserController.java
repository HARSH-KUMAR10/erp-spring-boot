package com.harshkumar093.erp.controller;

import com.harshkumar093.erp.model.UserModel;
import com.harshkumar093.erp.service.UserService;
import com.harshkumar093.erp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public Response<Long> createUser(@RequestBody UserModel userModel){
        return userService.create(userModel);
    }

    @GetMapping()
    public Response<List<UserModel>> getAllUser(){
        return userService.readAll();
    }

    @DeleteMapping("{id}")
    public Response<UserModel> deleteUser(@PathVariable long id){
        return userService.delete(id);
    }

}
