package com.mcsw.receiptapp.model;

public enum RoleEnum
{

    ADMIN("ADMIN"),
    USER("USER"),

    AUDITOR("AUDITOR");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleEnum findByValue(String value) {
        RoleEnum response = null;
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                response = role;
                break;
            }
        }
        return response;
    }
}
