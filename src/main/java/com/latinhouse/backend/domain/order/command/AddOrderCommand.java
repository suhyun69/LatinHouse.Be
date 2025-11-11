package com.latinhouse.backend.domain.order.command;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.order.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddOrderCommand extends SelfValidating<AddOrderCommand> {

    @NotBlank(message = "id cannot be blank.")
    private String id;

    @NotNull(message = "lessonNo cannot be blank.")
    private Long lessonNo;

    @NotNull(message = "lessonOptionSeq cannot be blank.")
    private Long lessonOptionSeq;

    @NotNull(message = "status cannot be null.")
    private OrderStatus status;

    @Builder
    public AddOrderCommand(Long lessonOptionSeq) {
        this.lessonOptionSeq = lessonOptionSeq;
    }

    public void validate() {
        this.validateSelf();
    }
}
