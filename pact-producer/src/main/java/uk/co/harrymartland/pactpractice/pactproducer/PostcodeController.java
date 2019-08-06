package uk.co.harrymartland.pactpractice.pactproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostcodeController {

private final PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/postcode/{postcode}")
    private PostCodeResult getPostcode(@PathVariable String postcode){
        return postcodeService.getPostcode(postcode);
    }
}
