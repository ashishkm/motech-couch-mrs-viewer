package org.motechproject.couchmrs.viewer.service;

import org.motechproject.couchmrs.viewer.domain.Attribute;
import org.motechproject.couchmrs.viewer.domain.Person;
import org.motechproject.couch.mrs.model.CouchPerson;
import org.motechproject.couch.mrs.repository.impl.AllCouchPersonsImpl;
import org.motechproject.mrs.domain.MRSAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final AllCouchPersonsImpl allCouchPersons;

    @Autowired
    public PersonService(AllCouchPersonsImpl allCouchPersons) {
        this.allCouchPersons = allCouchPersons;
    }

    public Person getFor(String personId) {
        if(personId == null) {
            return null;
        }

        CouchPerson couchPerson = allCouchPersons.findByPersonId(personId).get(0);

        List<Attribute> attributes = new ArrayList<Attribute>();
        for(MRSAttribute attribute : couchPerson.getAttributes()) {
            attributes.add(new Attribute(attribute.getName(), attribute.getValue()));
        }

        return new Person(couchPerson.getId(), couchPerson.getPersonId(), couchPerson.getFirstName(), couchPerson.getMiddleName(), couchPerson.getLastName(), couchPerson.getPreferredName(), couchPerson.getAddress(), couchPerson.getDateOfBirth(), couchPerson.getBirthDateEstimated(), couchPerson.getAge(), couchPerson.getGender(), couchPerson.isDead(), couchPerson.getDeathDate(), attributes);
    }
}
