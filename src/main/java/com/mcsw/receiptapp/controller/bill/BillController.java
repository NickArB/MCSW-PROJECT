package com.mcsw.receiptapp.controller.bill;

import com.mcsw.receiptapp.model.Bill;
import com.mcsw.receiptapp.model.User;
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

    @GetMapping("/users/{userEmail}")
    public List<Bill> allByUser(@PathVariable String userEmail){
        return billService.findAllByUser(userEmail);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Bill> findById( @PathVariable String id ){
        return ResponseEntity.ok(billService.findById(id));
    }


    @GetMapping( "/status/{userEmail}/{paymentStatus}" )
    public List<Bill> findByPaymentStatus( @PathVariable String userEmail, @PathVariable String paymentStatus ) {

        return billService.findByPaymentStatus(userEmail, paymentStatus);
    }

    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody BillDto billDto){
        return ResponseEntity.ok(billService.createBill(new Bill(billDto)));
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Bill> update(@RequestBody BillDto billDto, @PathVariable String id){
        Bill existingBill = billService.findById(id);
        if (existingBill != null) {
            return ResponseEntity.ok(billService.updateBill(new Bill(billDto),id));
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        return ResponseEntity.ok(billService.deleteBill(id));
    }

}
