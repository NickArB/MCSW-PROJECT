package com.mcsw.receiptapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mcsw.receiptapp.model.Bill;

public class BillRepository {
    private final JdbcTemplate jdbcTemplate;

    public BillRepository(){
        this.jdbcTemplate = new JdbcTemplate(DataSourceConfig.createDataSource());
    }

    public void insertBill(Bill bill) throws DataAccessException{
        String sql = "INSERT INTO bills (id, userId, company, billing_date, deadline, debt, payment_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bill.getPaymentID(), bill.getCostumer(), bill.getCompany(),
                            bill.getBillingDate(), bill.getDeadLine(), bill.getDebtToPay(), bill.getState());
    }

    public Bill findById(String id) {
        String sql = "SELECT * FROM bills WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Bill.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Bill> findAll(){
        String sql = "SELECT * FROM bills";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bill.class));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Bill updateBill(Bill newBill, String id) throws DataAccessException{
        String sql = "UPDATE bills SET userId=?, company=?, billing_date=?, deadline=?, payment_status=? WHERE id = ?";
        jdbcTemplate.update(sql, newBill.getCostumer(), newBill.getCompany(), 
                                    newBill.getBillingDate(), newBill.getDeadLine(), newBill.getState(), id);
        return findById(id);
    }

    public void deleteBill(String id){
        String sql = "DELETE FROM bills WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}