package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Patient;
import org.motechproject.couch.mrs.model.CouchPatientImpl;
import org.motechproject.couch.mrs.repository.AllCouchPatients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final AllCouchPatients allCouchPatients;
    private final PersonService personService;
    private final FacilityService facilityService;
    private final EncounterService encounterService;

    @Autowired
    public PatientService(AllCouchPatients allCouchPatients, PersonService personService, FacilityService facilityService, EncounterService encounterService) {
        this.allCouchPatients = allCouchPatients;
        this.personService = personService;
        this.facilityService = facilityService;
        this.encounterService = encounterService;
    }

    public Patient findByMotechId(String motechId) {
        if(motechId == null) {
            return null;
        }
        return getSinglePatient(allCouchPatients.findByMotechId(motechId));
    }

    private Patient getSinglePatient(List<CouchPatientImpl> couchPatients) {
        if(couchPatients.size() == 0) {
            return null;
        }

        CouchPatientImpl couchPatient = couchPatients.get(0);

        return new Patient(couchPatient.getId(), couchPatient.getPatientId(), couchPatient.getMotechId(), personService.getFor(couchPatient.getPersonId()), facilityService.getFor(couchPatient.getFacilityId()), encounterService.getForMotechId(couchPatient.getMotechId()));
    }

    public Patient findByPatientId(String patientId) {
        if(patientId == null) {
            return null;
        }
        return getSinglePatient(allCouchPatients.findByPatientId(patientId));
    }
}
