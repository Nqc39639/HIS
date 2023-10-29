package com.neuedu.his.entity;

import lombok.Data;

@Data
public class DisposalRequest {
    private Integer registerId;
    private Integer medicalTechnologyId;
    private String creationTime;
    private String disposalState;
    private Integer expenseState;
    private String techName;
    private Double techPrice;
    private String caseNumber;
    private String realName;
    private String cardNumber;
    private String gender;
    private Integer age;
}
