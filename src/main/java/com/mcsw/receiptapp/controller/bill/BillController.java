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
    public List<Bill> all(){
        return billService.findAll();
    }

    @GetMapping( "/{id}" )
    public Bill findById( @PathVariable String id ){
        return billService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody BillDto billDto){
        System.out.println(billDto.toString());
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
