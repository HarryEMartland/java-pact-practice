package uk.co.harrymartland.pactpractice.pactconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PactConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PactConsumerApplication.class, args);
    }

    @Bean
    RestTemplate postcodeRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
                .rootUri("http://localhost:8081")
                .build();
    }
}
