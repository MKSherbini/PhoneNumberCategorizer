package com.mksherbini.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String phone;
    private String countryName;
    private int countryCode;
    private boolean phoneState;
}
