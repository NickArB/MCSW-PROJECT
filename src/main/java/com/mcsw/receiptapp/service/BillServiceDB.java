package com.mcsw.receiptapp;

import java.util.List;

public class BillServiceDB implements BillService{

    private final DBTest dbService = new DBTest();

    @Override
    public boolean create(Bill bill) {
        
        return false;
    }

    @Override
    public boolean delete(String paymentID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Bill> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bill findByID(String paymentID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Bill bill, String paymentID) {
        // TODO Auto-generated method stub
        return false;
    }
    


}
