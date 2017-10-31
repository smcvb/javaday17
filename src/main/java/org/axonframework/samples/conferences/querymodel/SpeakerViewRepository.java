package org.axonframework.samples.conferences.querymodel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerViewRepository extends JpaRepository<SpeakerView, String> {

}
