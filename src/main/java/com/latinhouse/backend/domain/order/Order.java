package com.latinhouse.backend.domain.order;

import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private Long lessonNo;
    private Long lessonOptionNo;
    private OrderStatus status;

    public static Order from(AddOrderCommand cmd) {
        return Order.builder()
                .id(cmd.getId())
                .lessonNo(cmd.getLessonNo())
                .lessonOptionNo(cmd.getLessonOptionNo())
                .status(cmd.getStatus())
                .build();
    }
}
