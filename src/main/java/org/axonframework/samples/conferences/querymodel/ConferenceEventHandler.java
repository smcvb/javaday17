package org.axonframework.samples.conferences.querymodel;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.samples.conferences.coreapi.ConferenceCreatedEvent;
import org.axonframework.samples.conferences.coreapi.TalkAddedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConferenceEventHandler {

    private final ConferenceViewRepository conferenceViewRepository;
    private final SpeakerViewRepository speakerViewRepository;

    public ConferenceEventHandler(ConferenceViewRepository conferenceViewRepository,
                                  SpeakerViewRepository speakerViewRepository) {
        this.conferenceViewRepository = conferenceViewRepository;
        this.speakerViewRepository = speakerViewRepository;
    }

    @EventHandler
    public void on(ConferenceCreatedEvent event) {
        conferenceViewRepository.save(new ConferenceView(event.getConferenceId(), event.getConferenceName()));
    }

    @EventHandler
    public void on(TalkAddedEvent event) {
        String speaker = event.getSpeaker();
        SpeakerView speakerView = speakerViewRepository.findOne(speaker);

        List<String> talks;
        if (speakerView == null) {
            talks = new ArrayList<>();
        } else {
            talks = speakerView.getTalks();
        }

        talks.add(event.getTalkTitle());
        speakerViewRepository.save(new SpeakerView(speaker, talks));
    }
}
