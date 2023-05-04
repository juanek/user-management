package ar.com.juanek;

import ar.com.juanek.config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
public class ApplicationContextTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void context(){
        System.out.println(context);
    }

}
