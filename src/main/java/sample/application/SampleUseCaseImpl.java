package sample.application;

import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.application.mapper.SampleAppMapper;
import sample.domain.Sample;
import sample.domain.service.SampleService;
import sample.port.in.SampleUseCase;
import sample.port.in.dto.GetSampleAppResponse;

@Service
@RequiredArgsConstructor
public class SampleUseCaseImpl implements SampleUseCase {

    private final SampleAppMapper sampleAppMapper;
    private final SampleService sampleService;

    @Override
    public GetSampleAppResponse getSample(String id) {

        Sample sample = sampleService.getSample(id)
                .orElseThrow(() -> new NotFoundException("Sample not found"));

        return sampleAppMapper.toAppRes(sample, GetSampleAppResponse.class);
    }
}
