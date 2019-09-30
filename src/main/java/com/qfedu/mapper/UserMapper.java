package com.qfedu.mapper;

import com.qfedu.entry.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespace(blocking = true)

public interface UserMapper {


    @Select("select * from user")
    @Results(id = "userMap",
            value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "password", column = "password"),
            @Result(property = "code", column = "code"),
            @Result(property = "nickName", column = "nickName"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "address", column = "address"),
            @Result(property = "imgUrl", column = "imgUrl"),
            @Result(property = "createTime", column = "createTime"),
            })
    List<User> selectByAll();

    @Select("select* from user where id = #{id}")
    @ResultMap("userMap")
    User selectUserById(Integer id);


    @Insert("insert into user(email,phoneNum,password,code,nickName,sex,birthday,address,imgUrl,createTime) values (#{email},#{phoneNum},#{password},#{code},#{nickName},#{sex},#{birthday},#{address},#{imgUrl},#{createTime})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = Integer.class, before = false, statement = {"select last_insert_id()"})
    int insertIntoUser(User user);


    @Update("update user set email = #{email}, phoneNum = #{phoneNum} where id = #{id}")
    int updateUserById(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUserById(Integer id);

    @Select("select count(1) from user")
    int seleteNum();

    @Select("select * from user where birthday like #{birthday}")
    List<User> mohuchaxun(String birthday);


    @Select("select * from user where email = #{email}")
    @ResultMap("userMap")
    User selectUserByEmail(String email);


    @Select("select * from user where email = #{email} and password = #{password}")
    @ResultMap("userMap")
    User selectUserByEmailAndPassword(User user);

    @Update("update user set imgUrl = #{imgUrl} where email = #{email}")
    int updateUser(User user);


    @Update("update user set nickName = #{nickName}, sex = #{sex}, birthday =#{birthday}, address = #{address}  where email = #{email}")
    void updateUser2(User user);


    @Update("update user set password = #{password} where email = #{email} ")
    void updateUserPassword(User user);

}
