motech-couch-mrs-viewer
=======================

Visualization for MOTECH Couch MRS

This is a web application which fetched patient details from MOTECH Couch MRS and displays in HTML/JSON/XML format.

<br>Note:
<br>1. Modify couchdb.properties and tenant.properties as per your configuration.


URLS:
<br><b>HTML View::</b> <pre>&lt;host&gt;:&lt;port&gt;/&lt;context-path&gt;/patient/&lt;motech-id&gt;</pre>
<br><b>XML/JSON::</b> <pre>&lt;host&gt;:&lt;port&gt;/&lt;context-path&gt;/patient/raw/&lt;motech-id&gt;</pre>
(Default is JSON. Need to provide "Accept:application/xml" header for XML data. Can also provide "Accept:application/json" for data in JSON format.)


