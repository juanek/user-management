package ar.juanek;

import ar.juanek.mapper.PermissionMapper;
import ar.juanek.mapper.RolesMapper;
import ar.juanek.mapper.UserMapper;
import ar.juanek.model.Permission;
import ar.juanek.model.Role;
import ar.juanek.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DBConfigTest.class })
@TestPropertySource("classpath:application.properties")
//@TestExecutionListeners()
//@SqlConfig()
public class MyAppTest {
    private static final Logger LOG = LoggerFactory.getLogger(MyAppTest.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    RolesMapper rolesMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void myAppTest() {
        LOG.debug("hello test ");
        assertThat(true).isTrue();
        assertThat(userMapper).isNotNull();

        User user = userMapper.findById(1);

        System.out.println(user);

        System.out.println(rolesMapper);

        System.out.println(permissionMapper);

        System.out.println(userMapper.findAll());
        System.out.println(rolesMapper.findAll());

        List<Role> lista = userMapper.findRolesByUserId(1);
        assertThat(lista.size()).isEqualTo(1);
        System.out.println(lista);
        System.out.println(lista.get(0).getCreatedAt());

        List<Permission> listPermissions = rolesMapper.findPermissionsByRoleId(2);
        assertThat(listPermissions.size()).isEqualTo(2);
        System.out.println(listPermissions);
    }
}
