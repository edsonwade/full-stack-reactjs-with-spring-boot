package code.with.vanilson;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/fraud-check")
public record FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<FraudCheckResponse> isFraudulent(@PathVariable("customerId") Integer customerId) {
        var isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return ResponseEntity
                .ok()
                .body(
                        new FraudCheckResponse(isFraudulentCustomer));
    }
}
