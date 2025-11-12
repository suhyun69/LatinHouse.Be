package com.latinhouse.backend.domain.order.service;

import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.port.out.order.CreateOrderPort;
import com.latinhouse.backend.port.out.order.ReadOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CreateOrderPort createOrderPort;
    private final ReadOrderPort readOrderPort;

    public Order create(AddOrderCommand cmd) {
        return createOrderPort.create(Order.from(cmd));
    }

    public Optional<Order> getOrder(String id) {
        return readOrderPort.getOrder(id);
    }
}
