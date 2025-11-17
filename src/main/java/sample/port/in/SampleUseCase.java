package sample.port.in;

import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import sample.port.in.dto.GetSampleAppResponse;

public interface SampleUseCase {
    GetSampleAppResponse getSample(String id);
}
