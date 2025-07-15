package com.bvn.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    // Since we can have large no of consumers, we specify the groupID to manage and identity
    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            // Perform any business logic related to analytics here

            log.info("Received PatientEvent: [PatientId={}, PatientName={}, PatientEmail={}]",
                    patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail());


        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }
}
