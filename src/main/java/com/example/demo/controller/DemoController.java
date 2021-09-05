package com.example.demo.controller;

import com.example.demo.service.CurrencyService;
import com.example.demo.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DemoController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private GifService gifService;

    @GetMapping("/rates/{baseCurrencyCode}")
    public String getGif(@PathVariable("baseCurrencyCode") String baseCurrencyCode) {
        String result = currencyService.processRates(baseCurrencyCode);
        String url = gifService.getGifUrl(result);
        return "redirect:" + url;
    }
}
