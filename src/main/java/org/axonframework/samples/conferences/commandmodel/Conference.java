package org.axonframework.samples.conferences.commandmodel;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.samples.conferences.coreapi.AddTalkCommand;
import org.axonframework.samples.conferences.coreapi.ConferenceCreatedEvent;
import org.axonframework.samples.conferences.coreapi.CreateConferenceCommand;
import org.axonframework.samples.conferences.coreapi.DuplicateTalkException;
import org.axonframework.samples.conferences.coreapi.TalkAddedEvent;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class Conference {

    @AggregateIdentifier
    private String conferenceId;
    private List<String> talks;

    public Conference() {
        // Required by Axon
    }

    @CommandHandler
    public Conference(CreateConferenceCommand command) {
        AggregateLifecycle.apply(new ConferenceCreatedEvent(command.getConferenceId(), command.getConferenceName()));
    }

    @CommandHandler
    public void handle(AddTalkCommand command) throws DuplicateTalkException {
        String talkTitle = command.getTalkTitle();
        if (talks.contains(talkTitle)) {
            throw new DuplicateTalkException();
        }

        AggregateLifecycle.apply(new TalkAddedEvent(conferenceId, talkTitle, command.getSpeaker()));
    }

    @EventSourcingHandler
    public void on(ConferenceCreatedEvent event) {
        conferenceId = event.getConferenceId();
        talks = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(TalkAddedEvent event) {
        talks.add(event.getTalkTitle());
    }
}
