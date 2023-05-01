package ar.juanek;

import ar.juanek.mapper.UserMapper;
import ar.juanek.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyApp {
    private static final Logger LOG = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {
        LOG.info("hello");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ar.juanek");
        context.refresh();
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();

        String password = "admin";
        // Hash a password for the first time
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);

// gensalt's log_rounds parameter determines the complexity
// the work factor is 2**log_rounds, and the default is 10
        // String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

// Check that an unencrypted password matches one that has
// previously been hashed
        String candidate = "alice-password";
        hashed = "$2a$10$ezJDL4CP0jMOsXO/0SmvJetPMehGdiIDYzh2SKsfkW5c57Bz33o2m";
        if (BCrypt.checkpw(candidate, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");

        UserMapper userMapper = context.getBean(UserMapper.class);

        User user = userMapper.findById(1);

        System.out.println(user);


    }
}
