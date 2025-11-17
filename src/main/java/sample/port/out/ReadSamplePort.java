package sample.port.out;

import sample.domain.Sample;

import java.util.List;
import java.util.Optional;

public interface ReadSamplePort {
    Optional<Sample> getSample(String id);
    List<Sample> getSamples();
}
