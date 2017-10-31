package org.axonframework.samples.conferences.querymodel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceViewRepository extends JpaRepository<ConferenceView, String> {

}
