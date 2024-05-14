package com.mcsw.receiptapp.repository;

import com.mcsw.receiptapp.model.Card;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class CardRepository {
    private final JdbcTemplate jdbcTemplate;

    public CardRepository() {
        this.jdbcTemplate = new JdbcTemplate(DataSourceConfig.createDataSource());
    }

    public Card findByUserId(String userId, Card card) {
        String sql = "SELECT * FROM cards WHERE owner_id = ? AND account_number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Card.class), userId, card.getAccountNumber());
        } catch (DataAccessException e) {
            return null;
        }
    }

}
