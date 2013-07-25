package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couch.mrs.model.CouchObservation;
import org.motechproject.couchmrs.viewer.domain.Encounter;
import org.motechproject.couchmrs.viewer.domain.Observation;
import org.motechproject.couch.mrs.model.CouchEncounterImpl;
import org.motechproject.couch.mrs.repository.impl.AllCouchEncountersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EncounterService {

    private final FacilityService facilityService;
    private final ProviderService providerService;
    private final AllCouchEncountersImpl allCouchEncounters;

    @Autowired
    public EncounterService(FacilityService facilityService, ProviderService providerService, AllCouchEncountersImpl allCouchEncounters) {
        this.facilityService = facilityService;
        this.providerService = providerService;
        this.allCouchEncounters = allCouchEncounters;
    }

    public List<Encounter> getForMotechId(String motechId) {

        List<Encounter> encounters = new ArrayList<Encounter>();
        List<CouchEncounterImpl> couchEncounters = allCouchEncounters.getAll();
        for(CouchEncounterImpl couchEncounter : couchEncounters) {
            if(!motechId.equals(couchEncounter.getPatientId())) {
                continue;
            }
            encounters.add(map(couchEncounter));
        }
        return encounters;
    }

    private Encounter map(CouchEncounterImpl couchEncounter) {
        Set<CouchObservation> couchObservations = couchEncounter.getObservations();
        List<Observation> observations = new ArrayList<Observation>();
        for (CouchObservation couchObservation : couchObservations) {
            observations.add(ObservationMapper.map(couchObservation));
        }

        Encounter encounter = new Encounter(couchEncounter.getId(), couchEncounter.getEncounterId(), couchEncounter.getEncounterType(), facilityService.getFor(couchEncounter.getFacilityId()), providerService.getFor(couchEncounter.getProviderId()), couchEncounter.getPatientId(), observations, couchEncounter.getCreatorId(), couchEncounter.getDate());
        return encounter;
    }



}
