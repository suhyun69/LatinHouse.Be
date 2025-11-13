package com.latinhouse.backend.adapter.out.persistence.order.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.order.entity.OrderJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderStrategy implements
        DomainToEntityStrategy<Order, OrderJpaEntity>,
        EntityToDomainStrategy<OrderJpaEntity, Order> {

    @Override
    public boolean domainToEntitySupports(Class<?> c, Class<?> d) {
        return Order.class.isAssignableFrom(c)
                && OrderJpaEntity.class.isAssignableFrom(d);
    }

    @Override
    public OrderJpaEntity toEntity(Order order) {
        return OrderJpaEntity.builder()
                .id(order.getId())
                .lessonNo(order.getLessonNo())
                .lessonOptionNo(order.getLessonOptionNo())
                .status(order.getStatus().getCode())
                .build();
    }

    @Override
    public boolean entityToDomainSupports(Class<?> c, Class<?> d) {
        return OrderJpaEntity.class.isAssignableFrom(c)
                && Order.class.isAssignableFrom(d);
    }

    @Override
    public Order toDomain(OrderJpaEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .lessonNo(entity.getLessonNo())
                .lessonOptionNo(entity.getLessonOptionNo())
                .status(OrderStatus.of(entity.getStatus()))
                .build();
    }

}
