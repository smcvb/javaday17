package org.axonframework.samples.conferences.querymodel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConferenceViewController {

    private final ConferenceViewRepository conferenceViewRepository;
    private final SpeakerViewRepository speakerViewRepository;

    public ConferenceViewController(ConferenceViewRepository conferenceViewRepository,
                                    SpeakerViewRepository speakerViewRepository) {
        this.conferenceViewRepository = conferenceViewRepository;
        this.speakerViewRepository = speakerViewRepository;
    }

    @GetMapping("/conferences")
    public List<ConferenceView> conferences() {
        return conferenceViewRepository.findAll();
    }

    @GetMapping("/speaker/{speaker}")
    public SpeakerView speaker(@PathVariable String speaker) {
        return speakerViewRepository.findOne(speaker);
    }
}

