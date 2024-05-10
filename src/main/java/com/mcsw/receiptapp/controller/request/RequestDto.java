package com.mcsw.receiptapp.controller.request;

public class RequestDto {
    private String paymentId;
    private String newValue;
    private String requestState;
    
    public String getRequestState() {
        return requestState;
    }

    public RequestDto(){}

    public String getPaymentId() {
        return paymentId;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
    }

    @Override
    public String toString() {
        return "RequestDto [paymentId=" + paymentId + ", newValue=" + newValue + ", requestState=" + requestState + "]";
    }
}
