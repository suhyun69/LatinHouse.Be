package sample.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sample.domain.command.AddSampleCommand;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
    private String id;

    public static Sample from(AddSampleCommand cmd) {
        return Sample.builder()
                .id(cmd.getId())
                .build();
    }
}
