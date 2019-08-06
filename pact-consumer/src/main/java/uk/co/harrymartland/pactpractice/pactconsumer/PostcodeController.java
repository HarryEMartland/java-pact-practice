package uk.co.harrymartland.pactpractice.pactconsumer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostcodeController {

    private final PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/postcode/{postcode}")
    protected String getPostcode(@PathVariable String postcode, Model model) {
        var pc = postcodeService.getPostCode(postcode);
        model.addAttribute("postcode", pc);
        return "postcode";
    }
}
