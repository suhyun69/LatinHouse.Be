package com.latinhouse.backend.adapter.in.web.checkout;

import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebResponse;
import com.latinhouse.backend.adapter.in.web.checkout.mapper.CheckoutWebMapper;
import com.latinhouse.backend.adapter.in.web.lesson.dto.GetLessonWebResponse;
import com.latinhouse.backend.port.in.checkout.CheckoutUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/checkout")
@Tag(name = "Checkout", description = "Checkout API Document")
@RequiredArgsConstructor
public class ApiV1CheckoutController {

    private final CheckoutWebMapper checkoutWebMapper;
    private final CheckoutUseCase checkoutUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<GetCheckoutWebResponse> getCheckout(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(checkoutWebMapper.toWebRes(checkoutUseCase.getCheckout(id)));
    }
}
