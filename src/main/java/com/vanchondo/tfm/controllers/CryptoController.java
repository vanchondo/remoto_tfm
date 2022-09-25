package com.vanchondo.tfm.controllers;

import com.apptasticsoftware.rssreader.Item;
import com.vanchondo.tfm.configs.properties.CryptoProperties;
import com.vanchondo.tfm.dtos.CryptoDTO;
import com.vanchondo.tfm.dtos.CryptoValuesDTO;
import com.vanchondo.tfm.dtos.RssItem;
import com.vanchondo.tfm.services.CryptoForecasting;
import com.vanchondo.tfm.services.RSSClient;
import com.vanchondo.tfm.services.YahooFinanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    private final static Logger logger = LogManager.getLogger();

    private final YahooFinanceService yahooFinanceService;
    private final CryptoForecasting cryptoForecastingService;
    private final RSSClient rssClient;

    public CryptoController(YahooFinanceService yahooFinanceService, CryptoForecasting cryptoForecastingService, RSSClient rssClient){
        this.yahooFinanceService = yahooFinanceService;
        this.cryptoForecastingService = cryptoForecastingService;
        this.rssClient = rssClient;
    }

    @GetMapping(value="/news")
    public ResponseEntity<List<RssItem>> cryptoNews() throws IOException {
        return ResponseEntity.ok(rssClient.getRssItems());
    }

    @GetMapping(value="/history/btc")
    public ResponseEntity<CryptoValuesDTO> btcHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadBTCHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictBTC();

        return ResponseEntity.ok(new CryptoValuesDTO(history, prediction));
    }

    @GetMapping(value="/history/eth")
    public ResponseEntity<CryptoValuesDTO> ethHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadETHHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictETH();

        return ResponseEntity.ok(new CryptoValuesDTO(history, prediction));
    }

    @GetMapping(value="/history/ada")
    public ResponseEntity<CryptoValuesDTO> adaHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadADAHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictADA();

        return ResponseEntity.ok(new CryptoValuesDTO(history, prediction));
    }

    @GetMapping(value="/history/xrp")
    public ResponseEntity<CryptoValuesDTO> xrpHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadXRPHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictXRP();

        return ResponseEntity.ok(new CryptoValuesDTO(history, prediction));
    }

    @GetMapping(value="/history/sol")
    public ResponseEntity<CryptoValuesDTO> solHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadSOLHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictSOL();

        return ResponseEntity.ok(new CryptoValuesDTO(history, prediction));
    }
}
