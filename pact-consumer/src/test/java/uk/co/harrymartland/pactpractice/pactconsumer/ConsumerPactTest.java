package uk.co.harrymartland.pactpractice.pactconsumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactFolder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import java.util.Map;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@ExtendWith(PactConsumerTestExt.class)
@PactFolder("build/pacts")
@PactTestFor(providerName = "postcode-provider")
public class ConsumerPactTest {

    @Pact(consumer = "postcode-consumer", provider = "postcode-provider")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
                .given("A GET request with valid postcode")
                .uponReceiving("A GET request with valid postcode")
                .path("/postcode/m503bb")
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                        .stringMatcher("parish", ".*", "Salford, unparished area")
                        .stringMatcher("longitude", "-?[0-9]\\d*(\\.\\d+)?", "-2.294011")
                        .stringMatcher("latitude", "-?[0-9]\\d*(\\.\\d+)?", "53.473014")
                        .asBody())
                .toPact();

    }

    @Test
    @PactTestFor(pactMethod = "createPact", providerName = "postcode-provider")
    void testPact(MockServer mockServer) {

        PostCodeResult result = new PostcodeService(new RestTemplateBuilder().rootUri(mockServer.getUrl()).build()).getPostCode("m503bb");

        Assert.assertEquals("Salford, unparished area", result.getParish());
        Assert.assertEquals("53.473014", result.getLatitude());
        Assert.assertEquals("-2.294011", result.getLongitude());
    }
}