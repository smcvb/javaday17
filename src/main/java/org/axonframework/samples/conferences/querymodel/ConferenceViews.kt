package org.axonframework.samples.conferences.querymodel

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ConferenceView(
        @Id val conferenceId: String? = null,
        val conferenceName: String? = null
)

@Entity
data class SpeakerView(
        @Id val speaker: String? = null,
        @ElementCollection val talks: MutableList<String> = mutableListOf()
)
