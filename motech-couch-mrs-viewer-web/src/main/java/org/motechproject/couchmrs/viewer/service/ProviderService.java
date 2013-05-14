package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Provider;
import org.motechproject.couch.mrs.model.CouchProviderImpl;
import org.motechproject.couch.mrs.repository.impl.AllCouchProvidersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    private final AllCouchProvidersImpl allCouchProviders;
    private final PersonService personService;

    @Autowired
    public ProviderService(AllCouchProvidersImpl allCouchProviders, PersonService personService) {
        this.allCouchProviders = allCouchProviders;
        this.personService = personService;
    }

    public Provider getFor(String providerId) {
        if(providerId == null) {
            return null;
        }

        CouchProviderImpl couchProvider = allCouchProviders.findByProviderId(providerId).get(0);
        return new Provider(couchProvider.getId(), couchProvider.getProviderId(), personService.getFor(couchProvider.getPersonId()));
    }
}
