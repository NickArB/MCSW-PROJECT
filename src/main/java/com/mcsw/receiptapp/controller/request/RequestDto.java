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

    @Override
    public String toString() {
        return "RequestDto [paymentId=" + paymentId + ", newValue=" + newValue + ", requestState=" + requestState + "]";
    }
}
