package com.mcsw.receiptapp.controller.bill;

import com.mcsw.receiptapp.exception.InvalidCompanyException;
import com.mcsw.receiptapp.exception.InvalidCostException;
import com.mcsw.receiptapp.exception.InvalidDeadLineException;
import com.mcsw.receiptapp.model.Bill;
import com.mcsw.receiptapp.service.BillService;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping( "/bills" )
public class BillController{

    private final BillService billService = new BillService();

    @GetMapping("/users")
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
    public ResponseEntity<?> create(@RequestBody BillDto billDto){
        billDto = sanitize(billDto);
        try {
            Bill bill = billService.createBill(new Bill(billDto));
            return ResponseEntity.ok(bill);
        } catch (InvalidCostException e) {
            return new ResponseEntity<>("Error, el costo debe ser un número positivo.", HttpStatus.BAD_REQUEST);
        } catch (InvalidCompanyException e) {
            return new ResponseEntity<>("Error, la empresa que emite la factura no puede ser nula.", HttpStatus.BAD_REQUEST);
        } catch (InvalidDeadLineException e){
            return new ResponseEntity<>("Error, la fecha límite de pago no debe ser nula.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Bill> update(@RequestBody BillDto billDto, @PathVariable String id) throws ParseException{
        billDto = sanitize(billDto);
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

    private BillDto sanitize(BillDto input){
        input.setUserEmail(Jsoup.clean(input.getUserEmail(), Safelist.relaxed()));
        input.setCompany(Jsoup.clean(input.getCompany(), Safelist.relaxed()));
        input.setDebt(Jsoup.clean(input.getDebt(), Safelist.relaxed()));
        return input;
    }
}
