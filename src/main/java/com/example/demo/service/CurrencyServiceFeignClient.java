package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "currencyServiceFeignClient", url = "${openexchangerates.base.url}")
public interface CurrencyServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json", consumes = "application/json")
    String getLatestRate(@RequestParam("app_id") String appId,
                         @RequestParam("base") String baseCurrencyCode,
                         @RequestParam("symbols") String exchangeCurrencyCode);

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json", consumes = "application/json")
    String getPreviousRate(@PathVariable("date") String date,
                           @RequestParam("app_id") String appId,
                           @RequestParam("base") String baseCurrencyCode,
                           @RequestParam("symbols") String exchangeCurrencyCode);
}
