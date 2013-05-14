package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Encounter;
import org.motechproject.couchmrs.viewer.domain.Observation;
import org.motechproject.couch.mrs.model.CouchEncounterImpl;
import org.motechproject.couch.mrs.repository.impl.AllCouchEncountersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EncounterService {

    private final ObservationService observationMapper;
    private final FacilityService facilityService;
    private final ProviderService providerService;
    private final AllCouchEncountersImpl allCouchEncounters;

    @Autowired
    public EncounterService(ObservationService observationMapper, FacilityService facilityService, ProviderService providerService, AllCouchEncountersImpl allCouchEncounters) {
        this.observationMapper = observationMapper;
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
        List<Observation> observations = new ArrayList<Observation>();
        Set<String> observationIds = couchEncounter.getObservationIds();
        if(observationIds != null && observationIds.size() > 0) {
            for(String observationId: observationIds) {
                observations.add(observationMapper.getFor(observationId));
            }
        }

        Encounter encounter = new Encounter(couchEncounter.getId(), couchEncounter.getEncounterId(), couchEncounter.getEncounterType(), facilityService.getFor(couchEncounter.getFacilityId()), providerService.getFor(couchEncounter.getProviderId()), couchEncounter.getPatientId(), observations, couchEncounter.getCreatorId(), couchEncounter.getDate());
        return encounter;
    }



}
