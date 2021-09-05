package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${openexchangerates.appId}")
    private String appId;
    @Value("${demo.exchangeCurrencyCode}")
    private String exchangeCurrencyCode;

    @Autowired
    private CurrencyServiceFeignClient serviceFeignClient;

    @Override
    public String processRates(String baseCurrencyCode) {
        double currentRate = this.getCurrentRate(baseCurrencyCode);
        double previousRate = this.getPreviousRate(baseCurrencyCode);
        if (currentRate > previousRate) {
            return "rich";
        } else if (currentRate < previousRate) {
            return "broke";
        } else {
            return "keep-calm";
        }
    }

    @Override
    public double getCurrentRate(String baseCurrencyCode) {
        String response = serviceFeignClient.getLatestRate(appId, baseCurrencyCode, exchangeCurrencyCode);
        JSONObject json = new JSONObject(response);
        JSONObject rates = (JSONObject) json.get("rates");
        BigDecimal currentRate = (BigDecimal) rates.get(exchangeCurrencyCode);
        return currentRate.doubleValue();
    }
    @Override
    public double getPreviousRate(String baseCurrencyCode) {
        LocalDate previousDate = LocalDate.now().minusDays(1L);
        String response = serviceFeignClient.getPreviousRate(previousDate.toString(), appId, baseCurrencyCode, exchangeCurrencyCode);
        JSONObject json = new JSONObject(response);
        JSONObject rates = (JSONObject) json.get("rates");
        BigDecimal previousRate = (BigDecimal) rates.get(exchangeCurrencyCode);
        return previousRate.doubleValue();
    }
}
