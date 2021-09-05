package com.example.demo.service;

public interface CurrencyService {

    String processRates(String baseCurrencyCode);
    double getCurrentRate(String baseCurrencyCode);
    double getPreviousRate(String baseCurrencyCode);
}
