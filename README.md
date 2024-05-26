# Team Event Manager - SCARECROWS

Simple team event manager app for creations and listing of upcoming events.

### REST API
The application provides REST-ish endpoints for managing events and their attendees.
Rest API documentation can be found under following link.

### Authentication
Authentication and user management is preformed within a separate service. Link

### Rabbit MQ bindings
1. create exchange `registration-request-exchange`
2. set exchange binding to key `registration-requests-key`
