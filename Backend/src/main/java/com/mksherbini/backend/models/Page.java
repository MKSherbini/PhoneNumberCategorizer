package com.mksherbini.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int number;
    private int size;
    private int totalPages;
    private int totalElements;

    public Page(int number, int size) {
        this.number = number;
        this.size = size;
    }
}
