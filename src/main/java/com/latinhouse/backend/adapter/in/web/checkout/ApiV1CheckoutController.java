package com.latinhouse.backend.adapter.in.web.checkout;

import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebResponse;
import com.latinhouse.backend.adapter.in.web.checkout.dto.PaymentWebRequest;
import com.latinhouse.backend.adapter.in.web.checkout.dto.PaymentWebResponse;
import com.latinhouse.backend.adapter.in.web.checkout.mapper.CheckoutWebMapper;
import com.latinhouse.backend.domain.user.CustomUserDetails;
import com.latinhouse.backend.port.in.checkout.CheckoutUseCase;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public ResponseEntity<PaymentWebResponse> payment(@PathVariable("id") String id, @RequestBody PaymentWebRequest webReq, @AuthenticationPrincipal CustomUserDetails userDetails) {

        PaymentAppRequest appReq = PaymentAppRequest.builder()
                .checkoutId(id)
                .discounts(webReq.getDiscounts())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(checkoutWebMapper.toWebRes(checkoutUseCase.payment(appReq, userDetails.getUser())));
    }
}
