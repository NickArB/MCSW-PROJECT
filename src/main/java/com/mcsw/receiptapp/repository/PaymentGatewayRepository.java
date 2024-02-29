package com.mcsw.receiptapp.repository;

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
    }
}
