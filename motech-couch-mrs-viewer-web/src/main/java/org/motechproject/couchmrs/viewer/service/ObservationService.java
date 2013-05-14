package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Observation;
import org.motechproject.couch.mrs.model.CouchObservationImpl;
import org.motechproject.couch.mrs.repository.impl.AllCouchObservationsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ObservationService {

    private final AllCouchObservationsImpl allCouchObservations;


    @Autowired
    public ObservationService(AllCouchObservationsImpl allCouchObservations) {
        this.allCouchObservations = allCouchObservations;
    }

    public Observation getFor(String observationId) {

        if(observationId == null) {
            return null;
        }

        CouchObservationImpl couchObservation = allCouchObservations.findByObservationId(observationId).get(0);

        List<Observation> dependentObservations  = new ArrayList<Observation>();
        Set<String> dependentObservationIds = couchObservation.getDependantObservationIds();
        if(dependentObservationIds != null && dependentObservationIds.size() > 0) {
            for(String dependentObservationId: dependentObservationIds) {
                dependentObservations.add(this.getFor(dependentObservationId));
            }
        }

        return new Observation(couchObservation.getId(), couchObservation.getObservationId(), couchObservation.getConceptName(), getObservationValue(couchObservation), couchObservation.getPatientId(), dependentObservations, couchObservation.getDate());
    }

    private String getObservationValue(CouchObservationImpl couchObservation) {
        Object value = couchObservation.getValue();
        if(value == null) {
            return null;
        }
        return value.toString();
    }
}
