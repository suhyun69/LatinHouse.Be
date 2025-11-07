package com.latinhouse.backend.domain.order.command;

import com.latinhouse.backend.common.SelfValidating;
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

    @NotBlank(message = "lessonOptionNo cannot be blank.")
    private Long lessonOptionNo;

    @NotBlank(message = "status cannot be blank.")
    private String status;

    @Builder
    public AddOrderCommand(Long lessonNo, Long lessonOptionNo, String status) {
        this.lessonNo = lessonNo;
        this.lessonOptionNo = lessonOptionNo;
        this.status = status;
    }

    public void validate() {
        this.validateSelf();
    }
}
