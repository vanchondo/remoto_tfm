package com.vanchondo.tfm.services;

import com.vanchondo.tfm.configs.properties.CryptoForecastingConfiguration;
import com.vanchondo.tfm.dtos.CryptoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CryptoForecasting {

    private final CryptoForecastingConfiguration configuration;
    private final RestTemplate restTemplate;

    public CryptoForecasting(CryptoForecastingConfiguration configuration, RestTemplate restTemplate){
        this.configuration = configuration;
        this.restTemplate = restTemplate;
    }

    public List<CryptoDTO> predictBTC(){
        String url = configuration.getUrl() + "btc";
        return restTemplate.getForObject(url, List.class);
    }

    public List<CryptoDTO> predictETH(){
        String url = configuration.getUrl() + "eth";
        return restTemplate.getForObject(url, List.class);
    }

    public List<CryptoDTO> predictADA(){
        String url = configuration.getUrl() + "ada";
        return restTemplate.getForObject(url, List.class);
    }

    public List<CryptoDTO> predictXRP(){
        String url = configuration.getUrl() + "xrp";
        return restTemplate.getForObject(url, List.class);
    }

    public List<CryptoDTO> predictSOL(){
        String url = configuration.getUrl() + "sol";
        return restTemplate.getForObject(url, List.class);
    }

}
