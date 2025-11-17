package sample.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.domain.Sample;
import sample.domain.command.AddSampleCommand;
import sample.port.out.CreateSamplePort;
import sample.port.out.ReadSamplePort;
import sample.port.out.UpdateSamplePort;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final CreateSamplePort createSamplePort;
    private final ReadSamplePort readSamplePort;
    private final UpdateSamplePort updateSamplePort;

    public Sample create(AddSampleCommand cmd) { return createSamplePort.create(Sample.from(cmd)); }
    public Optional<Sample> getSample(String id)  { return readSamplePort.getSample(id); }
    public List<Sample> getSamples() { return readSamplePort.getSamples(); }
    public Sample update(Sample toBe) { return updateSamplePort.update(toBe); }
}
