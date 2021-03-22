package com.techartworks.covid.covid19info;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@ConstructorBinding
@Configuration
@ConfigurationProperties(prefix = "covid")
@Getter
@Setter
public class CovidConfigProperties {
    private String url;
}
