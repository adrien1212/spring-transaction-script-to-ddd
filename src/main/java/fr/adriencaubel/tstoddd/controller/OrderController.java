package fr.adriencaubel.tstoddd.controller;

import fr.adriencaubel.tstoddd.controller.dto.CreateOrderRequestModel;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateOrderRequestModel request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Order received: article=" + request.articleId());
    }
}
