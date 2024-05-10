package com.mcsw.receiptapp.repository;

import com.mcsw.receiptapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

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
        String insertSql = "INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(insertSql, user.getName(), user.getLastName(), user.getEmail(), user.getPasswordHash(), user.getRole(), user.getCreatedAt());
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
    public List<User> all(){
        String sql = "SELECT * FROM users";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public User update(String id, User user) {
        String sql = "UPDATE users SET name=?, lastName=?, email=?, passwordHash=?, role=?, createdAt=? WHERE email=?";
        try {
            jdbcTemplate.update(sql, user.getName(), user.getLastName(), user.getEmail(), user.getPasswordHash(), user.getRole(), user.getCreatedAt(), id);
            return user;
        } catch (Exception e) {
            // Manejar la excepción según sea necesario
            return null;
        }
    }

    public boolean delete(String email) {
        String sql = "DELETE FROM users WHERE email=?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, email);
            return rowsAffected > 0;
        } catch (Exception e) {
            return false;
        }
    }

}
