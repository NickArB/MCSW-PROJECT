package com.mcsw.receiptapp.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mcsw.receiptapp.model.Bill;
import com.mcsw.receiptapp.repository.BillRepository;

public class BillService {

    private static final BillRepository repo = new BillRepository();

    public Bill createBill(Bill bill){
        repo.insertBill(bill);
        return bill;
    }

    public Bill findById(String paymentID){
        try {
            return repo.findById(paymentID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Bill> findAll(){
        List<Bill> bills = repo.findAll();
        for(Bill b:bills){
            System.out.println(b.toString());
        }
        return bills;
    }

    public Bill updateBill(Bill bill, String paymentID){
        return repo.updateBill(bill, paymentID);
    }

    public boolean deleteBill(String paymentID){
        try {
            repo.deleteBill(paymentID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
