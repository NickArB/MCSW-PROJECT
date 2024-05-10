package com.mcsw.receiptapp.repository;

import com.mcsw.receiptapp.model.Card;
import com.mcsw.receiptapp.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mcsw.receiptapp.model.PaymentGateway;

public class PaymentGatewayRepository {
    private final JdbcTemplate jdbcTemplate;

    public PaymentGatewayRepository(){
        this.jdbcTemplate = new JdbcTemplate(DataSourceConfig.createDataSource());
    }

    public void applyPayment(PaymentGateway payment){
        String sql = "UPDATE bills SET payment_status= ? WHERE id = ?";
        jdbcTemplate.update(sql, payment.getFinalState(), payment.getPaymentId());

        String sql2 = "SELECT * FROM cards WHERE account_number = ?";
        Card cardToPay = jdbcTemplate.queryForObject(sql2, new BeanPropertyRowMapper<>(Card.class), payment.getAccountNumber());

        int newBalance = cardToPay.getAvailableBalance() - Integer.parseInt(payment.getDebt());

        String sql3 = "UPDATE cards SET available_balance= ? WHERE account_number = ?";
        jdbcTemplate.update(sql3, newBalance, payment.getAccountNumber());

    }
}
