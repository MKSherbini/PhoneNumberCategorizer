package com.mksherbini.backend.models;

import lombok.Data;

@Data
public class CountryPhone {
    private String name;
    private int code;
    private String phoneRegex;
}
