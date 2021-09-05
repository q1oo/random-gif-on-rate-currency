package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gifServiceFeignClient", url = "${giphy.base.url}")
public interface GifServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random", consumes = "application/json")
    String getRandomGif(@RequestParam("api_key") String apiKey,
                        @RequestParam("tag") String tag);
}
