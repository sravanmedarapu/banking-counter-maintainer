package com.counter.maintainer.config;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/*@Configuration*/
public class DataSourceConfig {

    private static final String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir");

    /*@Bean(name = "mainDataSource")
    @Primary*/
    public DataSource createMainDataSource() {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:"+TEMP_DIRECTORY+"/bankDB;MODE=MySQL");
        return ds;
    }

}