package com.example.covid19.service;

import com.example.covid19.model.CovidData;
import com.example.covid19.model.CovidDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class CovidService {

    @Value("${rapid-api.key}")
    private String apiKey;

    @Value("${rapid-api.host}")
    private String apiHost;

    @Value("${covid.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CovidService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CovidData getCovidData(String country) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("country", country)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", apiHost);  // Set the host header
        headers.set("x-rapidapi-key", apiKey);    // Set the API key header
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CovidDataResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CovidDataResponse.class);
            CovidDataResponse responseBody = response.getBody();
            if (responseBody != null && responseBody.getResponse() != null && responseBody.getResponse().length > 0) {
                return responseBody.getResponse()[0];  // Access the first item in the array
            } else {
                // Handle the case where no data is returned
                System.err.println("No data available for the requested country.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}