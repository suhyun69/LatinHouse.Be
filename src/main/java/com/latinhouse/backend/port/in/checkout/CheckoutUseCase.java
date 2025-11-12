package com.latinhouse.backend.port.in.checkout;

import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;

public interface CheckoutUseCase {
    GetCheckoutAppResponse getCheckout(String id);
}
