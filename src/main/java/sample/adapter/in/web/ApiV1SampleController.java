package sample.adapter.in.web;

import com.latinhouse.backend.adapter.in.web.lesson.dto.GetLessonWebResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.adapter.in.web.dto.GetSampleWebResponse;
import sample.adapter.in.web.mapper.SampleWebMapper;
import sample.port.in.SampleUseCase;

@RestController
@RequestMapping("/api/v1/samples")
@Tag(name = "Sample", description = "Sample API Document")
@RequiredArgsConstructor
public class ApiV1SampleController {

    private final SampleUseCase sampleUseCase;
    private final SampleWebMapper sampleWebMapper;

    @GetMapping("/{id}")
    public ResponseEntity<GetSampleWebResponse> getSample(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sampleWebMapper.toWebRes(sampleUseCase.getSample(id)));
    }
}
