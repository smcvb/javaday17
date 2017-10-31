package org.axonframework.samples.conferences.commandmodel;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.samples.conferences.coreapi.AddTalkCommand;
import org.axonframework.samples.conferences.coreapi.CreateConferenceCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ConferenceController {

    private final CommandGateway commandGateway;

    public ConferenceController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping("/hello")
    public String helloKyiv() {
        return "Hello Kyiv!";
    }

    @GetMapping
    public void createConference() {
        String conferenceId = UUID.randomUUID().toString();
        commandGateway.send(new CreateConferenceCommand(conferenceId, "Javaday Kyiv"), LoggingCallback.INSTANCE);
        commandGateway.send(new AddTalkCommand(conferenceId, "How do you code?", "Alice"), LoggingCallback.INSTANCE);
        commandGateway.send(new AddTalkCommand(conferenceId, "Clean coding", "Bob"), LoggingCallback.INSTANCE);
        commandGateway.send(new AddTalkCommand(conferenceId, "Axon Framework", "Steven"), LoggingCallback.INSTANCE);
//        commandGateway.send(new AddTalkCommand(conferenceId, "Axon Framework", "Allard"), LoggingCallback.INSTANCE);
    }
}
