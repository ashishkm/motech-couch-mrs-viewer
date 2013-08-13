package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Observation;
import org.motechproject.mrs.domain.MRSObservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ObservationMapper {

    public static Observation map(MRSObservation couchObservation) {
        Set<MRSObservation> dependantMRSObservations = couchObservation.getDependantObservations();
        List<Observation> dependentObservations = new ArrayList<Observation>();
        for (MRSObservation dependantObservation : dependantMRSObservations) {
            dependentObservations.add(map(dependantObservation));
        }
        return new Observation(couchObservation.getObservationId(), couchObservation.getConceptName(), couchObservation.getValue().toString(), couchObservation.getPatientId(), dependentObservations, couchObservation.getDate());

    }
}
