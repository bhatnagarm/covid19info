package com.techartworks.covid.covid19info;

import com.techartworks.covid.covid19info.domain.model.CountryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CovidController {

    private final static Logger log = LoggerFactory.getLogger(CovidController.class);
    private final RestTemplate restTemplate;


    public CovidController(final RestTemplate restTemplate) {
        Assert.notNull(restTemplate, "RestTemplate should not be null");
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/covidinfo")
    public CountryInfo[] covidInfo() {
        log.warn("Call to Covid Rest API");
        return restTemplate.getForObject("https://api.covid19api.com/live/country/south-africa/status/confirmed",
                CountryInfo[].class);
    }
}
