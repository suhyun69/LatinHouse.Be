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
    private Long seq;
    private String id;
    private Long lessonNo;
    private Long lessonOptionSeq;
    private String status;

    public static Order from(AddOrderCommand cmd) {
        return Order.builder()
                .id(cmd.getId())
                .lessonNo(cmd.getLessonNo())
                .lessonOptionSeq(cmd.getLessonOptionSeq())
                .status(cmd.getStatus())
                .build();
    }
}
