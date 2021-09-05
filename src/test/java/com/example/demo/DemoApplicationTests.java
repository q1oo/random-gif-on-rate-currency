package com.example.demo;

import com.example.demo.service.CurrencyServiceFeignClient;
import com.example.demo.service.GifServiceFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class DemoApplicationTests {

    @LocalServerPort
    private int port;
    @Value("${openexchangerates.appId}")
    private String appId;
    @Value("${demo.exchangeCurrencyCode}")
    private String exchangeCurrencyCode;
    private String baseCurrencyCode = "USD";
    @Value("${giphy.key}")
    private String apiKey;

    @Autowired
    CurrencyServiceFeignClient currencyServiceFeignClient;
    @Autowired
    GifServiceFeignClient gifServiceFeignClient;

    @Test
    public void testCurrencyServiceFeignClient() {
        assertThat(this.currencyServiceFeignClient.getLatestRate(appId, baseCurrencyCode, exchangeCurrencyCode)).contains(exchangeCurrencyCode);
        LocalDate previousDate = LocalDate.now().minusDays(1L);
        assertThat(this.currencyServiceFeignClient.getPreviousRate(previousDate.toString(), appId, baseCurrencyCode, exchangeCurrencyCode)).contains(exchangeCurrencyCode);
    }

    @Test
    public void testGifServiceFeignClient() {
        assertThat(this.gifServiceFeignClient.getRandomGif(apiKey, "rich")).contains("fixed_height_downsampled_url");
        assertThat(this.gifServiceFeignClient.getRandomGif(apiKey, "broke")).contains("fixed_height_downsampled_url");
    }

}
