package com.vanchondo.tfm.configs.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.vanchondo.yahoo-finance")
@Getter
@Setter
public class YahooFinanceConfiguration {
    private String url;
    private CryptoProperties btc;
}
