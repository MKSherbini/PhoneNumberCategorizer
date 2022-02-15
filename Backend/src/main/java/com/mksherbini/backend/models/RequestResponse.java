package com.mksherbini.backend.models;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RequestResponse<T> {
    private T data;
    private Map<String, String> links = new HashMap<>();
    private Page page;
}
