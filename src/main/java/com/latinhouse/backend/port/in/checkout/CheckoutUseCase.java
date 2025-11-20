package com.latinhouse.backend.port.in.checkout;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppResponse;

public interface CheckoutUseCase {
    GetCheckoutAppResponse getCheckout(String id);
    PaymentAppResponse payment(PaymentAppRequest paymentAppRequest, User user);
}
