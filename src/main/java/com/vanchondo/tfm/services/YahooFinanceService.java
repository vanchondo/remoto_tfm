package com.vanchondo.tfm.services;

import com.vanchondo.tfm.configs.properties.YahooFinanceConfiguration;
import com.vanchondo.tfm.dtos.CryptoDTO;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class YahooFinanceService {
    private final static Logger logger = LogManager.getLogger();

    private YahooFinanceConfiguration configuration;

    public YahooFinanceService(YahooFinanceConfiguration configuration){
        this.configuration = configuration;
    }

    public List<CryptoDTO> downloadBTCHistory() throws IOException {
        StringBuilder urlString = new StringBuilder(configuration.getUrl() + configuration.getBtc().getPath());
        return downloadHistory(urlString, configuration.getBtc().getStartDate());
    }

    public List<CryptoDTO> downloadETHHistory() throws IOException {
        StringBuilder urlString = new StringBuilder(configuration.getUrl() + configuration.getEth().getPath());
        return downloadHistory(urlString, configuration.getEth().getStartDate());
    }

    public List<CryptoDTO> downloadADAHistory() throws IOException {
        StringBuilder urlString = new StringBuilder(configuration.getUrl() + configuration.getAda().getPath());
        return downloadHistory(urlString, configuration.getAda().getStartDate());
    }

    public List<CryptoDTO> downloadXRPHistory() throws IOException {
        StringBuilder urlString = new StringBuilder(configuration.getUrl() + configuration.getXrp().getPath());
        return downloadHistory(urlString, configuration.getXrp().getStartDate());
    }

    public List<CryptoDTO> downloadSOLHistory() throws IOException {
        StringBuilder urlString = new StringBuilder(configuration.getUrl() + configuration.getSol().getPath());
        return downloadHistory(urlString, configuration.getSol().getStartDate());
    }

    private List<CryptoDTO> downloadHistory(StringBuilder urlString, String startDate) throws IOException{
        List<CryptoDTO> history = new ArrayList<>();

        String period1 = getEpoch(startDate);
        String period2 = getYesterdayEpoch();
        urlString.append("?period1=" + period1);
        urlString.append("&period2=" + period2);
        urlString.append("&interval=1d");
        urlString.append("&events=history");
        urlString.append("&includeAdjustedClose=true");

        URL url = new URL(urlString.toString());
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                String[] values = inputLine.split(",");
                if (values != null && values.length > 0 && !values[0].equals("Date") && !values[3].equals("null")) {
                    CryptoDTO dto = new CryptoDTO();
                    dto.setDate(values[0]);
                    dto.setOpen(values[1]);
                    dto.setHigh(values[2]);
                    dto.setLow(values[3]);
                    dto.setClose(values[4]);
                    dto.setVolume(values[5]);
                    history.add(dto);
                }
            }
            in.close();
        } catch (IOException ex){
            logger.error(ex);
        } finally {
            if (con != null){
                con.disconnect();
            }
        }
        return history;
    }

    private static String getEpoch(String dateStr) {
        long epoc = new Date().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date = df.parse(dateStr);
            epoc = date.getTime();
        } catch (ParseException e) {
            logger.error(e);
        }
        return String.valueOf(epoc).substring(0, 10);
    }

    private static String getYesterdayEpoch(){
        Date yesterday = DateUtils.addDays(new Date(), -1);
        return String.valueOf(yesterday.getTime()).substring(0, 10);
    }
}
