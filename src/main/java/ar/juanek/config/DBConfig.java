package ar.juanek.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@MapperScan("ar.juanek.mapper")
public class DBConfig {



    @Value("${datasource.url}")
    String url;

    @Value("${datasource.className}")
    String className;

    @Value("${datasource.username}")
    String username;

    @Value("${datasource.password}")
    String password;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(className);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;

    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        return factoryBean.getObject();
    }

//    @Bean
//    public ResourceDatabasePopulator databasePopulator() {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.setSqlScriptEncoding("UTF-8");
//        populator.addScripts(new ClassPathResource(schema));
//        populator.addScript(new ClassPathResource(data));
//        return populator;
//    }

//    @Bean
//    public InitializingBean populatorExecutor() {
//        return () -> DatabasePopulatorUtils.execute(databasePopulator(), getDataSource());
//    }
}
