package com.latinhouse.backend.adapter.out.persistence.order;

import com.latinhouse.backend.adapter.out.persistence.order.mapper.OrderPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.order.repository.OrderRepository;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.port.out.order.CreateOrderPort;
import com.latinhouse.backend.port.out.order.ReadOrderPort;
import com.latinhouse.backend.port.out.order.UpdateOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements CreateOrderPort, ReadOrderPort, UpdateOrderPort {

    private final OrderPersistenceMapper orderPersistenceMapper;
    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderPersistenceMapper.toDomain(orderRepository.save(orderPersistenceMapper.toEntity(order)));
    }

    @Override
    public Optional<Order> getOrder(String id) {
        return orderRepository.findById(id)
                .map(orderPersistenceMapper::toDomain);
    }
}
