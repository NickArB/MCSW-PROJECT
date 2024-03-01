package com.mcsw.receiptapp.controller.bill;

import com.mcsw.receiptapp.model.Bill;
import com.mcsw.receiptapp.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/bills" )
public class BillController{

    private final BillService billService = new BillService();

    @GetMapping
    public ResponseEntity<List<Bill>> all(){
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Bill> findById( @PathVariable String id ){
        return ResponseEntity.ok(billService.findById(id));
    }

    @GetMapping( "/status/{paymentStatus}" )
    public ResponseEntity<List<Bill>> findByPaymentStatus( @PathVariable String paymentStatus ){
        return ResponseEntity.ok(billService.findByPaymentStatus(paymentStatus));
    }

    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody BillDto billDto){
        return ResponseEntity.ok(billService.createBill(new Bill(billDto)));
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Bill> update(@RequestBody BillDto billDto, @PathVariable String id){
        return ResponseEntity.ok(billService.updateBill(new Bill(billDto),id));
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        return ResponseEntity.ok(billService.deleteBill(id));
    }

}
