package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserControer {
    

    @Resource
    UserMapper userMapper;

    @GetMapping
    public List<User> getUser(){
        return userMapper.findAll();
    }
    
    @PostMapping
    public String addUser(@RequestBody User user){
        userMapper.save(user);
        return "success";
    }

    @PutMapping
    public String updateUser(@RequestBody User user){
        userMapper.updateById(user);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userMapper.deleteById(id);
        return "success";
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
        return userMapper.findById(id);
    }

    @GetMapping("/login")
    public String login(String username, String password){
        if(username == null){
            return "登陆失败，用户名不允许为空";
        }

        if(password.length() == 0){
            return "密码不允许为空";
        }
        User user = userMapper.selectUser(username);
        
        if(user == null){
            return "登陆失败";
        }
            
        if(Objects.equals(password, user.getPassword())){
            return "登录成功";
        }
        return "登录失败,密码错误";
    }

    @GetMapping("/register")
    public String register(String username, String password){
        log.info("username:{}", username);
        log.info("password:{}", password);
        if(username.length() == 0){
            return "用户名不允许为空";
        }
        if(password.length() == 0){
            return "密码不允许为空";
        }
        User user = userMapper.selectUser(username);
        
        if(user != null){
            return "注册失败，用户已存在";
        }
        int resultword = userMapper.saveuser(username, password);
        if(resultword == 0){
            return "注册失败";
        }
        return "注册成功";
    }
}
