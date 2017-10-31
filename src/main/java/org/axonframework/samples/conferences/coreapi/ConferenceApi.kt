package org.axonframework.samples.conferences.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateConferenceCommand(
        val conferenceId: String,
        val conferenceName: String
)

data class AddTalkCommand(
        @TargetAggregateIdentifier val conferenceId: String,
        val talkTitle: String,
        val speaker: String
)

data class ConferenceCreatedEvent(
        val conferenceId: String,
        val conferenceName: String
)

data class TalkAddedEvent(
        val conferenceId: String,
        val talkTitle: String,
        val speaker: String
)

class DuplicateTalkException : Exception()
