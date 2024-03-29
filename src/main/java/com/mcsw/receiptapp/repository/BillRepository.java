package com.mcsw.receiptapp.repository;

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
        String sql = "INSERT INTO bills (userEmail, company, billing_date, deadline, debt, payment_status) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bill.getUserEmail(), bill.getCompany(),
                            bill.getBillingDate(), bill.getDeadLine(), bill.getDebt(), bill.getPaymentStatus());
    }

    public Bill findById(String id) {
        String sql = "SELECT * FROM bills WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Bill.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Bill> findByUser(String userEmail) {
        String sql = "SELECT * FROM bills WHERE useremail = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bill.class), userEmail);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Bill> findByPaymentStatus(String userEmail, String paymentStatus) {
        String sql = "SELECT * FROM bills WHERE useremail = ? AND payment_status = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bill.class), userEmail, paymentStatus);
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
        String sql = "UPDATE bills SET userEmail=?, company=?, billing_date=?, deadline=?, payment_status=?, debt=? WHERE id = ?";
        jdbcTemplate.update(sql, newBill.getUserEmail(), newBill.getCompany(), 
                                    newBill.getBillingDate(), newBill.getDeadLine(), newBill.getPaymentStatus(), newBill.getDebt(), id);
        return findById(id);
    }

    public void deleteBill(String id){
        String sql = "DELETE FROM bills WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
