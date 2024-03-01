package com.mcsw.receiptapp.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mcsw.receiptapp.model.Request;

public class RequestRepository {
    private final JdbcTemplate jdbcTemplate;

    public RequestRepository(){
        this.jdbcTemplate = new JdbcTemplate(DataSourceConfig.createDataSource());
    }

    public List<Request> findAll(){
        String sql = "SELECT * FROM requests";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Request.class));
    }

    public Request findById(String id){
        String sql = "SELECT * FROM requests WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Request.class), id);
    }

    private Request findByPaymentId(String id){
        String sql = "SELECT * FROM requests WHERE paymentId = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Request.class), id);
    }

    public Request createRequest(Request solicitud){
        String sql = "INSERT INTO requests (paymentId, newValue, requestState) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, solicitud.getPaymentId(), 
                                solicitud.getNewValue(), solicitud.getRequestState());
        return findByPaymentId(solicitud.getPaymentId());
    }

    public Request updateRequestState(String id, String newState){
        String sql = "UPDATE requests SET requestState = ? WHERE id = ?";
        jdbcTemplate.update(sql, newState, id);
        Request request = findById(id);
        if(newState.equals("APROBADA")){
            sql = "UPDATE bills SET debt = ? WHERE id = ?";
            jdbcTemplate.update(sql, request.getNewValue(), request.getPaymentId());
        }
        return request;
    }
}
