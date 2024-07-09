package com.example.covid19.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Case {

    private int total;
    private int active;
    private int critical;
    private int recovered;
    private int newCases;

}
