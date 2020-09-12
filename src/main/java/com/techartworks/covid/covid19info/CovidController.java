package com.techartworks.covid.covid19info;

import com.techartworks.covid.covid19info.domain.model.CountryInfo;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class CovidController {

    private final RestTemplate restTemplate;

    public CovidController(final RestTemplate restTemplate) {
        Assert.notNull(restTemplate, "RestTemplate should not be null");
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/covidinfo")
    public CountryInfo[] covidInfo(@RequestHeader Map<String, String> headers) {
        return restTemplate.getForObject("https://api.covid19api.com/live/country/south-africa/status/confirmed",
                CountryInfo[].class);
    }
}
