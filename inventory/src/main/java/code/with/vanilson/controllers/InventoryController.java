package code.with.vanilson.controllers;


import code.with.vanilson.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping(value = "/inventory/{sku_code}")
    public ResponseEntity<Boolean> findOrderById(@PathVariable(name = "sku_code") String skuCode) {
        return ResponseEntity.ok().body(inventoryService.isInStock(skuCode));
    }


}
