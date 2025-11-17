package sample.port.in.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class GetSampleAppRequest  extends SelfValidating<ApplyLessonAppRequest> {

    @NotBlank(message = "id cannot be blank")
    private String id;

    @Builder
    public GetSampleAppRequest(String id) {
        this.id = id;

        this.validateSelf();
    }
}
