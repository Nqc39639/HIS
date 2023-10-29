package com.neuedu.his.entity;

import lombok.Data;

@Data
public class DrugInfo {
    private Integer id;
    private String drugCode;
    private String drugName;
    private String drugFormat;
    private String manufacturer;
    private String drugType;
    private Double drugPrice;
    private String creationDate;
    private Integer quantity;
}