package ar.com.juanek.dao;


import ar.com.juanek.model.Role;
import ar.com.juanek.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUser(@Param("username") String userName);
    @Select("SELECT * FROM roles AS r INNER JOIN user_roles AS ur ON ur.role_id=r.id AND ur.user_id = #{id};")
    List<Role> findRolesByUserId(int id);

    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void insert(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(int id);
}
