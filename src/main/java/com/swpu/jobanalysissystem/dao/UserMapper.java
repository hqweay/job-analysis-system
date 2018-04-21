package com.swpu.jobanalysissystem.dao;


import com.swpu.jobanalysissystem.entity.User;
import com.swpu.jobanalysissystem.pojo.Login;
import org.apache.ibatis.annotations.*;

import javax.validation.Valid;

import java.util.List;
@Mapper
public interface UserMapper {

    //添加
    @Insert("Insert into user (name, email, password) values(#{name}, #{email}, #{password})")
    int addUserRegister(@Valid Login user);


    //如果查询为空，会返回null
    @Select("select * from user where #{email} = email or #{name} = name")
    Integer isRegistered(Login login);

    //如果查询为空，会返回null
    @Select("select * from user where name = #{name}")
    Integer isRegisteredByName(String name);
    //如果查询为空，会返回null
    @Select("select * from user where email = #{email}")
    Integer isRegisteredByEmail(String email);

    //如果查询为空，会返回null
    @Select("select * from user where #{email} = email and #{password} = password")
    Integer isLogined(Login login);

    //如果查询为空，会返回null
    @Select("select * from user where name = #{userName}")
    Login selectByUsernameLogin(String userName);

    //如果查询为空，会返回null
    @Select("select * from user where name = #{userName}")
    User selectByUsernameUser(String userName);


    @Select("select * from user where id = #{id}")
    User selectById(int id);


    @Select("select * from user")
    List<User> selectUser();
    @Select("select count(id) from user")
    int getCount();

    @Insert("insert into user (name, email, info_education, info_major, info_place, info_ability, info_experience," +
            "likes, password) values (#{name},#{email},#{info_education},#{info_major},#{info_place},#{info_ability},#{info_experience},#{likes},#{password})")
    int addUser(User user);

    @Update("UPDATE user set id = #{id}, name = #{name}, email = #{email}, password = #{password}, info_education = #{info_education},info_major = #{info_major},info_place = #{info_place}, info_ability = #{info_ability},info_experience = #{info_experience},likes = #{likes} where id = #{id}")
    int editUser(User user);

    @Update("UPDATE user set name = #{name}, email = #{email} where id = #{id}")
    int editUserTest(User user);

    @Delete("DELETE FROM user WHERE id = ${id}")
    int deleteUser(@Param(value="id") String id);
}