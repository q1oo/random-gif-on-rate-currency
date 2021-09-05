package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifServiceImpl implements GifService {

    @Value("${giphy.key}")
    private String apiKey;
    @Autowired
    private GifServiceFeignClient serviceFeignClient;

    @Override
    public String getGifUrl(String tag) {
        String response = serviceFeignClient.getRandomGif(apiKey, tag);
        JSONObject json = new JSONObject(response);
        JSONObject data = (JSONObject) json.get("data");
        String url = (String) data.get("fixed_height_downsampled_url");
        return url;
    }
}
