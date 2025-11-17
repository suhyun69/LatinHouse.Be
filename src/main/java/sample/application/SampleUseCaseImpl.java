package sample.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.application.mapper.SampleAppMapper;
import sample.domain.service.SampleService;
import sample.port.in.SampleUseCase;

@Service
@RequiredArgsConstructor
public class SampleUseCaseImpl implements SampleUseCase {

    private final SampleAppMapper sampleAppMapper;
    private final SampleService sampleService;

}
