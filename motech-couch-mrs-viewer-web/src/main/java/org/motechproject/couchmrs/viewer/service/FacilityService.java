package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Facility;
import org.motechproject.couch.mrs.model.CouchFacility;
import org.motechproject.couch.mrs.repository.impl.AllCouchFacilitiesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {
    private final AllCouchFacilitiesImpl allCouchFacilities;

    @Autowired
    public FacilityService(AllCouchFacilitiesImpl allCouchFacilities) {
        this.allCouchFacilities = allCouchFacilities;
    }

    public Facility getFor(String facilityId) {
        if(facilityId == null) {
            return null;
        }

        CouchFacility couchFacility = allCouchFacilities.findByFacilityId(facilityId).get(0);

        return new Facility(couchFacility.getId(), couchFacility.getFacilityId(), couchFacility.getName(), couchFacility.getRegion(),couchFacility.getCountyDistrict(), couchFacility.getStateProvince(),  couchFacility.getCountry());
    }
}
