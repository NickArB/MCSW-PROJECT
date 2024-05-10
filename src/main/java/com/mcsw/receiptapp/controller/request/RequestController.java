package com.mcsw.receiptapp.controller.request;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcsw.receiptapp.controller.auth.LoginDto;
import com.mcsw.receiptapp.model.Request;
import com.mcsw.receiptapp.service.RequestService;

@RestController
@RequestMapping( "/requests" )
public class RequestController {
    private final RequestService service = new RequestService();

    @GetMapping
    public ResponseEntity<List<Request>> all(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Request> findById( @PathVariable String id ){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Request> create(@RequestBody RequestDto dto){
        dto = sanitize(dto);
        return ResponseEntity.ok(service.createRequest(new Request(dto)));
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Request> update(@RequestBody RequestDto dto, @PathVariable String id){
        dto = sanitize(dto);
        return ResponseEntity.ok(service.updateRequest(new Request(dto),id));
    }

    private RequestDto sanitize(RequestDto input){
        input.setNewValue(Jsoup.clean(input.getNewValue(), Safelist.relaxed()));
        return input;
    }
}
