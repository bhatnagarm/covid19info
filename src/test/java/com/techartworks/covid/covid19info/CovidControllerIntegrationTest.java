package com.techartworks.covid.covid19info;

import com.techartworks.covid.covid19info.domain.model.CountryInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CovidControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

    @Test
    void getHello() {
        final ResponseEntity<String> response = template.getForEntity(base.toString()
                        + "/hello", String.class);
        assertEquals("Hello World", response.getBody());
    }

    @Test
    void testCovidInfo() {
        final CountryInfo[] response = template.getForObject(base.toString()
                + "/covidinfo", CountryInfo[].class);
        assertNotNull(response);
    }
}