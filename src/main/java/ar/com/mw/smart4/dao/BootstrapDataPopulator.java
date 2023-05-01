package ar.com.mw.smart4.dao;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * A data populator that creates a set of security tables and test data that can be used by the
 * Shiro Spring sample application to demonstrate the use of the
 * {@link org.apache.shiro.realm.jdbc.JdbcRealm} The tables created by this class follow the default
 * table and column names that {@link org.apache.shiro.realm.jdbc.JdbcRealm} uses.
 *
 * @author Les Hazlewood
 */
@Component
public class BootstrapDataPopulator implements InitializingBean
{

    private static final String CREATE_TABLES = "create table users (\n"
            + "    username varchar(255) primary key,\n" + "    password varchar(255) not null\n"
            + ");\n" + "\n" + "create table roles (\n" + "    role_name varchar(255) primary key\n"
            + ");\n" + "\n" + "create table user_roles (\n" + "    username varchar(255) not null,\n"
            + "    role_name varchar(255) not null,\n"
            + "    constraint user_roles_uq unique ( username, role_name )\n" + ");\n" + "\n"
            + "create table roles_permissions (\n" + "    role_name varchar(255) not null,\n"
            + "    permission varchar(255) not null,\n" + "    primary key (role_name, permission)\n"
            + ");";

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapDataPopulator.class);

    @Autowired
    protected DataSource dataSource = null;

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    public void afterPropertiesSet() throws Exception
    {
        // because we're using an in-memory hsqldb for the sample app, a new one will be created
        // each time the
        // app starts, so create the tables and insert the 2 sample users on bootstrap:

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(CREATE_TABLES);

        // password is 'user' SHA hashed and base64 encoded:
        // The first argument to the hash constructor is the actual value to be hased. The 2nd is
        // the
        // salt. In this simple demo scenario, the username and the password are the same, but to
        // clarify the
        // distinction, you would see this in practice:
        // new Sha256Hash( <password>, <username> )
        String query = "insert into users values ('user', '" + new Sha256Hash("user").toBase64() +
                "' )";
        jdbcTemplate.execute(query);
        LOGGER.debug("Created user.");

        // password is 'admin' SHA hashed and base64 encoded:
        query = "insert into users values ( 'admin', '" + new Sha256Hash("admin").toBase64() +
                "' )";
        jdbcTemplate.execute(query);
        LOGGER.debug("Created admin.");

        query = "insert into roles values ( 'user' )";
        jdbcTemplate.execute(query);
        LOGGER.debug("Created user");

        query = "insert into roles values ( 'admin' )";
        jdbcTemplate.execute(query);
        LOGGER.debug("Created admin");

        query = "insert into roles_permissions values ( 'user', 'view')";
        jdbcTemplate.execute(query);
        LOGGER.debug("Created permission view for role user");

        query = "insert into roles_permissions values ( 'admin', 'user:*')";
        jdbcTemplate.execute(query);
        LOGGER.debug("Created permission user:* for role admin");

        query = "insert into user_roles values ( 'user', 'user' )";
        jdbcTemplate.execute(query);
        LOGGER.debug("Assigned user role user");

        query = "insert into user_roles values ( 'admin', 'admin' )";
        jdbcTemplate.execute(query);
        LOGGER.debug("Assigned admin role admin");
    }

}

