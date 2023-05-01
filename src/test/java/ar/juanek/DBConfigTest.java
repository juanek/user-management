package ar.juanek;

import ar.juanek.config.DBConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@PropertySource("classpath:application.properties")
@MapperScan("ar.juanek.mapper")
public class DBConfigTest extends DBConfig {

    @Value("${schema}")
    String schema;

    @Value("${data}")
    String data;

    @Bean
    public ResourceDatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("UTF-8");
        populator.addScripts(new ClassPathResource(schema));
        populator.addScript(new ClassPathResource(data));
        return populator;
    }

    @Bean
    public InitializingBean populatorExecutor() {
        return () -> DatabasePopulatorUtils.execute(databasePopulator(), getDataSource());
    }
}
