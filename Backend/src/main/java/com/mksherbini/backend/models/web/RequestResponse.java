package com.mksherbini.backend.models.web;


import com.mksherbini.backend.models.Page;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RequestResponse<T> {
    private T data;
    private Map<String, String> links = new HashMap<>();
    private Page page;
}
