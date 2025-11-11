package com.latinhouse.backend.port.out.order;

import com.latinhouse.backend.domain.order.Order;

public interface CreateOrderPort {
    Order create(Order order);
}
