# Services status Monitor

The status monitor checks the statuses of the other services and shows them on the status page.

<p align="center">
  <img src="https://user-images.githubusercontent.com/49530516/130776315-4f944c20-b61f-4ddc-88b4-ea289478b013.jpg" alt="Size Limit CLI" width="738">
</p>
If service's status changed it will be automatically updated on the page.

<p align="center">
  <img src="https://user-images.githubusercontent.com/49530516/130794076-98f3889d-694f-4743-8f0a-1830597af27e.jpg
" alt="Size Limit CLI" width="738">
</p>

For now, status info implemented:
* Colored status icon
* JVM used memory percentage
* Short status
* Full status (Error descriptions)



## How It Works
[Services Status Monitor](https://github.com/SergioViacheslaev/services-status-monitor/tree/master/services-status-monitor-service/src/main/kotlin/com/servicesmonitor/servicesstatusmonitorservice)
uses REST Api for communication with monitored services:
* **Weather service** - simple service that shows weather page.
* **Processing service** - simple dummy service.

At first, it registers services, that want to be monitored (services send POST registration requests),
saves them in MongoDB. After that, by scheduled task, it performs a status check of the services making GET requests for special endpoints of the monitored services.

You can see **Swagger** panel of each service for more details.
* [Status Monitor - service-registration-controller](http://localhost:8080/swagger-ui/#/service-registration-controller)
* [Weather Service - service-status-controller ](http://localhost:8081/swagger-ui/#/service-status-controller)
* [Processing Service - service-status-controller ](http://localhost:8082/swagger-ui/#/service-status-controller)

## How to run the project ?
1. Install and run MongoDB on port 27017, or use [Docker image](https://hub.docker.com/_/mongo),

   docker run --name mongodb -d -p 27017:27017 mongo
2. Start Service-status-monitor service
3. Start Weather and Processing services
4. Open [services status information page](http://localhost:8080/services-monitor/services/status),
   but at first you need to log in.
<p align="center">
  <img src="https://user-images.githubusercontent.com/49530516/130956990-3e27ee7e-c732-4025-84ae-f0cbf5f182ad.jpg" alt="Size Limit CLI" width="738">
</p>
5. Register new user
<p align="center">
  <img src="https://user-images.githubusercontent.com/49530516/130957582-a2094acd-2aba-46f9-87da-4326602047d0.jpg" alt="Size Limit CLI" width="738">
</p>
6. After successful login, you will see the services status page.

## Technical stack
1. Kotlin
2. Spring: Boot, Security, Data
3. HTML, JS, Jquery, WebSockets
4. MongoDB
5. Gradle
