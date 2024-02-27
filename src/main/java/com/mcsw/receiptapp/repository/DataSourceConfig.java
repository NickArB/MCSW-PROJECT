package com.mcsw.receiptapp.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class DataSourceConfig {

    public static DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:/data/demo");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        return dataSource;
    }
}