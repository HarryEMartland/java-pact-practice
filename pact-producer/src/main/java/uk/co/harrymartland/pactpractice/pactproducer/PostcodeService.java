package uk.co.harrymartland.pactpractice.pactproducer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostcodeService {

    private final RestTemplate restTemplate;

    public PostcodeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri("https://api.postcodes.io")
                .build();
    }

    public PostCodeResult getPostcode(String postcode) {
        return restTemplate.getForObject("/postcodes/{postcode}", PostCodeResponse.class, postcode).getResult();
    }
}
