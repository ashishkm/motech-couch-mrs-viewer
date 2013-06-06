package org.motechproject.couchmrs.viewer.service;


import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.EventRelay;

public class DummyEventRelay implements EventRelay {
    @Override
    public void sendEventMessage(MotechEvent motechEvent) {
        //Do nothing.
    }
}
