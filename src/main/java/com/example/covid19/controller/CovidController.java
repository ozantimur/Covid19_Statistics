package com.example.covid19.controller;

import com.example.covid19.model.CovidData;
import com.example.covid19.service.CovidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
public class CovidController {

    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("/statistics")
    public CovidData getCovidStatistics(@RequestParam String country) {
        return covidService.getCovidData(country);
    }
}