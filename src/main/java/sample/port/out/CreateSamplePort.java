package sample.port.out;

import sample.domain.Sample;

public interface CreateSamplePort {
    Sample create(Sample sample);
}
