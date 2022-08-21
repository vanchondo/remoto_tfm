package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.configs.properties.CryptoProperties;
import com.vanchondo.tfm.dtos.CryptoDTO;
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

    public CryptoController(YahooFinanceService yahooFinanceService){
        this.yahooFinanceService = yahooFinanceService;
    }

    @GetMapping(value="/history")
    public ResponseEntity<List<CryptoDTO>> btcHistory() throws IOException {
        List<CryptoDTO> history = yahooFinanceService.downloadBTCHistory();
        return ResponseEntity.ok(history);
    }
}
