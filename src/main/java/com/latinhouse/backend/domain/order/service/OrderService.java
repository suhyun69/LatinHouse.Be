package com.latinhouse.backend.domain.order.service;

import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.port.out.order.CreateOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CreateOrderPort createOrderPort;

    public Order create(AddOrderCommand cmd) {
        return createOrderPort.create(Order.from(cmd));
    }
}
