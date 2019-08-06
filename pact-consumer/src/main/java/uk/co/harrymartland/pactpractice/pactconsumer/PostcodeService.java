package uk.co.harrymartland.pactpractice.pactconsumer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostcodeService {

    private final RestTemplate restTemplate;

    public PostcodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PostCodeResult getPostCode(String postcode){
        return restTemplate.getForObject("/postcode/{postcode}", PostCodeResult.class, postcode);
    }

}
