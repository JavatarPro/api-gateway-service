api-gateway-service
---

## What is this µService for?
An API Gateway is a single point of entry (and control) for front end clients, 
which could be browser based or mobile. The client only has to know the URL of 
API Gateway server, and the backend can be refactored at will with no change. 

The service wil be used for:

* Routing to µServices
* Authentication

The service is implemented with Spring boot and Zuul

## How does it work?
The µService acts as a reverse proxy:

* catch all incoming request (from UI, 3rd party Systems)
* route to a concrete backend service, tacking decision based on routing configuration:
    * default - use serviceID taken from Service Registry (Eureka) as URL identifier of backend service
    * using routing from configuration
    * custom filters
    
Zuul implementation is based on Filters which came into action in different steps of Request processing:

* PRE - executed before routing
* ROUTE - filters which make routing to backend services
* POST - executed after routed Request has beed processed by backend service
* ERROR

## How do I get set up?
1. You need to give links to Eureka and Configuration µServices
2. You need to configure routing to backend µServices 

### Basic Configuration

```
zuul:
  # make some services unavailable from external world 
  ignoredServices: 'integration-service, payment-service, configuration-service, eureka-service'
  routes:
    # requests ../api/mvc/.. go to mvc-service
    mvc-service: /api/mvc/**
```

## Dependencies
* eureka-service
* configuration-service