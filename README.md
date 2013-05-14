motech-couch-mrs-viewer
=======================

Visualization for MOTECH Couch MRS

This is a web application which fetched patient details from MOTECH Couch MRS and displays in HTML/JSON/XML format.

Note:
1. Modify couchdb.properties and tenant.properties as per your configuration.


URLS:
HTML View:: <host>:<port>/<context-path>/patient/<motech-id>
XML/JSON:: <host>:<port>/<context-path>/patient/raw/<motech-id> (Default is JSON. Need to provide Accept:application/xml header for XML data)

