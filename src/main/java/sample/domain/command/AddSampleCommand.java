package sample.domain.command;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.lesson.command.AddLessonCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddSampleCommand extends SelfValidating<AddSampleCommand> {
    @NotBlank(message = "id cannot be blank.")
    String id;

    @Builder
    public AddSampleCommand(String id) {
        this.id = id;

        this.validateSelf();
    }
}
