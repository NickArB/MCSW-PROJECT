package com.mcsw.receiptapp.model;

import com.mcsw.receiptapp.controller.request.RequestDto;

public class Request {
    private String id;
    private String paymentId;
    private String newValue;
    private String requestState;
    
    public Request(String id, String paymentId, String newValue) {
        this.id = id;
        this.paymentId = paymentId;
        this.newValue = newValue;
        this.requestState = "PENDIENTE";
    }

    public Request() {
    }

    public Request(RequestDto dto) {
        this.id = null;
        this.paymentId = dto.getPaymentId();
        this.newValue = dto.getNewValue();
        this.requestState = (dto.getRequestState() == null) ? "PENDIENTE" : dto.getRequestState();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getRequestState() {
        return requestState;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
    }

    @Override
    public String toString() {
        return "Request [id=" + id + ", paymentId=" + paymentId + ", newValue=" + newValue + ", requestState="
                + requestState + "]";
    }
}
