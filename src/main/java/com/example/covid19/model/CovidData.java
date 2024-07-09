package com.example.covid19.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidData {

    private String country;
    private Case cases;
    private Death deaths;
    private Test tests;

}