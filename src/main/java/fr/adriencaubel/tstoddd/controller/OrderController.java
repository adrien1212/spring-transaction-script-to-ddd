package fr.adriencaubel.tstoddd.controller;

import fr.adriencaubel.tstoddd.controller.dto.CreateOrderRequestModel;
import fr.adriencaubel.tstoddd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateOrderRequestModel request) {
        orderService.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Order received: article=" + request.articleId());
    }
}
