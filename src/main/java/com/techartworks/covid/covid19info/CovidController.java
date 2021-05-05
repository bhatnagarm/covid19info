package com.techartworks.covid.covid19info;

import com.techartworks.covid.covid19info.domain.model.CountryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
public class CovidController {

    private final static Logger log = LoggerFactory.getLogger(CovidController.class);
    private final RestTemplate restTemplate;
    private final CovidConfigProperties covidConfigProperties;


    public CovidController(final RestTemplate restTemplate, final CovidConfigProperties covidConfigProperties) {
        Assert.notNull(restTemplate, "RestTemplate should not be null");
        Assert.notNull(covidConfigProperties, "CovidConfigProperties should not be null");
        this.restTemplate = restTemplate;
        this.covidConfigProperties = covidConfigProperties;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World" ;
    }

    @GetMapping("/covidinfo")
    public CountryInfo[] covidInfo() {
        log.warn("Call to Covid Rest API");
        return restTemplate.getForObject(covidConfigProperties.getUrl(), CountryInfo[].class);
    }

    @GetMapping(value = "/covidasync")
    public CountryInfo[] covid19infoAsync() {
        log.info("Call to Covid19Info Rest API Non-Blocking !");
        final Mono<CountryInfo[]> requestedValue =  WebClient.create()
                .get()
                .uri(covidConfigProperties.getUrl())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CountryInfo[].class).log();
        return requestedValue.block();
    }
}
