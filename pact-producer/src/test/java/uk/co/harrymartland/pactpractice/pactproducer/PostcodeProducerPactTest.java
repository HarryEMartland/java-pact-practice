package uk.co.harrymartland.pactpractice.pactproducer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import java.util.Properties;
import java.util.Random;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(classes = PactProducerApplication.class, webEnvironment = RANDOM_PORT)
@PactBroker(host = "localhost", port = "9292")
@Provider("postcode-provider")
public class PostcodeProducerPactTest {

    @TestTarget
    public Target target;

    @MockBean
    private PostcodeService postcodeService;

    @LocalServerPort
    public void setPort(int port) {
        this.target = new HttpTarget(port);
    }

    @State("A GET request with valid postcode")
    public void validPostcode() {
        Random random = new Random();
        PostCodeResult postCodeResult = new PostCodeResult();
        postCodeResult.setParish("a parish");
        Double min = -4.;
        Double max = 4.;
        postCodeResult.setLongitude(Double.toString(min + (max - min) * random.nextDouble()));
        postCodeResult.setLatitude(Double.toString(min + (max - min) * random.nextDouble()));
        when(postcodeService.getPostcode( anyString()))
        .thenReturn(postCodeResult);
    }

    @Before
    public void setPactPublishResultsProperty() {
        /*
				  There is a bug in pact-jvm-provider-spring that means you can only verify provider pacts in the
				  PACT broker by explicitly setting the System Property below; it does not automatically detect
				  it from application.properties
				 */
        Properties prop = System.getProperties();
        prop.put("pact.verifier.publishResults", "true");
        System.setProperties(prop);
    }
}