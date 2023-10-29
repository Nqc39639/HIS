package com.neuedu.his.entity;

import lombok.Data;

@Data
public class Medicine {
    private Integer id;
    private Integer registerId;
    private String caseNumber;
    private String realName;
    private String cardNumber;
    private String homeAddress;
    private String employee;
    private Integer drugId;
    private String drugCode;
    private String drugName;
    private Double drugPrice;
    private Integer drugNumber;
    private String creationTime;
    private Integer expenseState;
    private Integer drugState;
}
