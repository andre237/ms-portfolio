package com.andre.training.core.domain.entity;

import lombok.Data;

@Data
public class Address {

    private String zipCode;
    private String streetName;
    private Integer houseNumber;
    private String complement;

}
