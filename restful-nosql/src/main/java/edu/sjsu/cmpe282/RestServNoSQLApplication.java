package edu.sjsu.cmpe282;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class RestServNoSQLApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(RestServNoSQLApplication.class, args);

        RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);

        restConfiguration.setReturnBodyOnCreate(true);

    }

}
