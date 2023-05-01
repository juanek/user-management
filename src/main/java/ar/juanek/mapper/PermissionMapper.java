package ar.juanek.mapper;

import ar.juanek.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PermissionMapper {

    @Insert("INSERT INTO permissions (name, description) VALUES (#{name}, #{description})")
    void insert(Permission permission);

    @Select("SELECT * FROM permissions WHERE id = #{id}")
    Permission findById(int id);

    @Select("SELECT * FROM permissions")
    List<Permission> findAll();

    @Update("UPDATE permissions SET name = #{name}, description = #{description} WHERE id = #{id}")
    void update(Permission permission);

    @Delete("DELETE FROM permissions WHERE id = #{id}")
    void delete(int id);

}
