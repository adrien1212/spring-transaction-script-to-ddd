package fr.adriencaubel.tstoddd.infrastructure.web;

import fr.adriencaubel.tstoddd.application.order.OrderUseCase;
import fr.adriencaubel.tstoddd.infrastructure.web.dto.CreateOrderRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateOrderRequestModel request) {
        orderUseCase.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Order received: article=" + request.articleId());
    }
}
