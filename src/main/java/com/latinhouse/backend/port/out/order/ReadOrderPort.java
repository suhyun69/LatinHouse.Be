package com.latinhouse.backend.port.out.order;

import com.latinhouse.backend.domain.order.Order;

import java.util.Optional;

public interface ReadOrderPort {
    Optional<Order> getOrder(String id);
}
