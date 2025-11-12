package com.latinhouse.backend.adapter.in.web.checkout;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/checkout")
@Tag(name = "Checkout", description = "Checkout API Document")
@RequiredArgsConstructor
public class ApiV1CheckoutController {
}
