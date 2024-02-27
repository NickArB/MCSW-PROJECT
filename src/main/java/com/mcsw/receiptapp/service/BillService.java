package com.mcsw.receiptapp;

import java.util.List;

public interface BillService {
    boolean create(Bill bill);

    Bill findByID(String paymentID);

    List<Bill> findAll();

    boolean update(Bill bill, String paymentID);

    boolean delete(String paymentID);
}
