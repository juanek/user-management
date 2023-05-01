package ar.juanek.mapper;

import ar.juanek.model.Permission;
import ar.juanek.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface RolesMapper {
    @Insert("INSERT INTO roles (name, description) VALUES (#{name}, #{description})")
    void insert(Role role);

    @Select("SELECT * FROM roles WHERE id = #{id}")
    Role findById(int id);

    @Select("SELECT * FROM roles")
    List<Role> findAll();

    @Update("UPDATE roles SET name = #{name}, description = #{description} WHERE id = #{id}")
    void update(Role role);

    @Delete("DELETE FROM roles WHERE id = #{id}")
    void delete(int id);

    @Select("SELECT p.id,p.name,p.description FROM role_permissions AS rp INNER JOIN permissions AS p ON rp.permission_id = p.id WHERE rp.role_id = #{id};")
    List<Permission> findPermissionsByRoleId(int id);
}
