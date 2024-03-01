package com.mcsw.receiptapp.service;

import java.util.List;

import com.mcsw.receiptapp.model.Request;
import com.mcsw.receiptapp.repository.RequestRepository;

public class RequestService {
    private static final RequestRepository repo = new RequestRepository();

    public List<Request> findAll(){
        return repo.findAll();
    }

    public Request findById(String id){
        return repo.findById(id);
    }

    public Request createRequest(Request request){
        return repo.createRequest(request);
    }

    public Request updateRequest(Request request, String id){
        return repo.updateRequestState(id, request.getRequestState());
    }
}
