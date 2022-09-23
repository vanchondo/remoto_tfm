package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.configs.properties.CryptoProperties;
import com.vanchondo.tfm.dtos.CryptoDTO;
import com.vanchondo.tfm.services.CryptoForecasting;
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

    private YahooFinanceService yahooFinanceService;
    private final CryptoForecasting cryptoForecastingService;

    public CryptoController(YahooFinanceService yahooFinanceService, CryptoForecasting cryptoForecastingService){
        this.yahooFinanceService = yahooFinanceService;
        this.cryptoForecastingService = cryptoForecastingService;
    }

    @GetMapping(value="/history/btc")
    public ResponseEntity<List<CryptoDTO>> btcHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadBTCHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictBTC();

        history.addAll(prediction);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value="/history/eth")
    public ResponseEntity<List<CryptoDTO>> ethHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadETHHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictETH();

        history.addAll(prediction);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value="/history/ada")
    public ResponseEntity<List<CryptoDTO>> adaHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadADAHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictADA();

        history.addAll(prediction);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value="/history/xrp")
    public ResponseEntity<List<CryptoDTO>> xrpHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadXRPHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictXRP();

        history.addAll(prediction);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value="/history/sol")
    public ResponseEntity<List<CryptoDTO>> solHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadSOLHistory();
        List<CryptoDTO> prediction = cryptoForecastingService.predictSOL();

        history.addAll(prediction);
        return ResponseEntity.ok(history);
    }
}
