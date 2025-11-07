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

    @NotNull(message = "lessonOptionSeq cannot be blank.")
    private Long lessonOptionSeq;

    @NotBlank(message = "status cannot be blank.")
    private String status;

    @Builder
    public AddOrderCommand(Long lessonNo, Long lessonOptionSeq, String status) {
        this.lessonNo = lessonNo;
        this.lessonOptionSeq = lessonOptionSeq;
        this.status = status;
    }

    public void validate() {
        this.validateSelf();
    }
}
