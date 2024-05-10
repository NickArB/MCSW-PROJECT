package com.mcsw.receiptapp.service;

import java.util.Date;
import java.util.List;

import com.mcsw.receiptapp.exception.InvalidCompanyException;
import com.mcsw.receiptapp.exception.InvalidCostException;
import com.mcsw.receiptapp.exception.InvalidDeadLineException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mcsw.receiptapp.model.Bill;
import com.mcsw.receiptapp.repository.BillRepository;

public class BillService {

    private static final BillRepository repo = new BillRepository();

    public Bill createBill(Bill bill) throws InvalidDeadLineException, InvalidCompanyException, InvalidCostException {
        if (bill.getDeadLine() == null || bill.getDeadLine().getTime() < new Date().getTime()) {
            throw new InvalidDeadLineException();
        }
        if (bill.getCompany() == null || bill.getCompany().isEmpty()) {
            throw new InvalidCompanyException();
        }
        try {
            Integer.parseInt(bill.getDebt());
        } catch (NumberFormatException e) {
            throw new InvalidCostException();
        }

        if (bill.getDebt() == null || Integer.valueOf(bill.getDebt()) < 1){
            throw new InvalidCostException();
        }

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

    public List<Bill> findByPaymentStatus(String userEmail,String paymentStatus) {
        return repo.findByPaymentStatus(userEmail, paymentStatus);
    }
    public List<Bill> findAll(){
        return repo.findAll();
    }

    public List<Bill> findAllByUser(String userEmail){
        return repo.findByUser(userEmail);
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
