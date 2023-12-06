package code.with.vanilson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/fraud-check")
@Slf4j
public record FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<FraudCheckResponse> isFraudulent(@PathVariable("customerId") Integer customerId) {
        var isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return ResponseEntity
                .ok()
                .body(
                        new FraudCheckResponse(isFraudulentCustomer));
    }
}
