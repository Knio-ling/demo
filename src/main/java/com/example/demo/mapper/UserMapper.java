package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

public interface UserMapper {
    @Select("SELECT * from user")
    List<User> findAll();

    @Update("INSERT INTO `test`.`user`(`username`, `password`, `email`, `phone`, `career`) VALUES (#{username}, #{password}, #{email}, #{phone}, #{career});")
    @Transactional
    void save(User user);

    @Update("update user set username=#{username}, password=#{password}, email=#{email}, phone=#{phone}, career=#{career} where id =#{id} ")
    @Transactional
    void updateById(User user);

    @Delete("delete from user where id=#{id}")
    void deleteById(Long id);

    @Select("select * from user where id=#{id}")
    User findById(Long id);
    
    @Insert("INSERT INTO `test`.`user`(username, password) VALUES (#{username}, #{password})")
    int saveuser(@Param("username") String username, @Param("password") String password);

    @Select("select id,username,password from user where username=#{username}")
    User selectUser(@Param("username") String username);

    @Select("select id,username,password from user where password=#{password}")
    User getPassword(@Param("password") String password);
}
