package io.github.dotyocode.libraryApi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    // @Bean
    public DataSource datasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    }

    /**
     * configuracao Hikary
     * https://github.com/brettwooldridge/HikariCP
     * 
     * @return
     */

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();

        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(10); // maximo de conexoes liberadas
        config.setMinimumIdle(1); // tamanho inciial do pool
        config.setPoolName("library-db-pool");
        config.setConnectionTimeout(100000); // timeout para tentar conexao
        config.setConnectionTestQuery("select 1"); // query de teste

        return new HikariDataSource(config);
    }
}
