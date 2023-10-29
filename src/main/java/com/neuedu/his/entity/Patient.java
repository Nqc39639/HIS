package com.neuedu.his.entity;

import lombok.Data;

@Data
public class Patient {
    private Integer id;
    private String caseNumber;
    private String realName;
    private String gender;
    private Integer age;
    private String birthdate;
    private String address;
    private String idNumber;
    private String visitDate;
    private String noon;
    private Integer registLevel;
    private Integer departmentId;
    private String deptmentName;
    private Integer doctorId;
    private String doctorName;
    private Integer initialQuota;
    private Integer usedQuota;
    private String onOffSwitch;
    private Integer amountDue;
    private Integer paymentMethodId;
    private Integer visitState;
}