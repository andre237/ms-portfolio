package com.andre.training.core.domain.entity;

import com.andre.training.core.domain.exception.InvalidEmailException;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class Email {

    private String address;
    private Boolean marketingConsent = Boolean.FALSE;

    public Email(String address) {
        var emailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        if (!emailPattern.matcher(address).matches()) throw new InvalidEmailException();
        this.address = address;
    }
}
