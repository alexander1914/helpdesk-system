package br.com.system.helpdesk.config;


import br.com.system.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instanciaDB() {
        if (value.equals("create")) {
            this.dbService.instanciaDB();
        }
        return false;
    }
}
