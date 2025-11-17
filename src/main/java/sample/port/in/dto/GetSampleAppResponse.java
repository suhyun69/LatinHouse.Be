package sample.port.in.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSampleAppResponse {
    private String id;
}
