package com.mcsw.receiptapp.repository;

import com.mcsw.receiptapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(){
        this.jdbcTemplate = new JdbcTemplate(DataSourceConfig.createDataSource());
    }

    public User save(User user) {
        String checkSql = "SELECT COUNT(*) FROM users WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, user.getEmail());
        if (count > 0) {
            return null;
        }
        String insertSql = "INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(insertSql, user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPasswordHash(), user.getRole(), user.getCreatedAt());
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
