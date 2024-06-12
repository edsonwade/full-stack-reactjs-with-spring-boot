package code.with.vanilson.customer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomerController
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    @GetMapping
    public String hello() {
        return "Hello World im logged";
    }
}