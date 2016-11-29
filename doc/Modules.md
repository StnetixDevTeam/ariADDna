# Modules project ariADDna
### M1 - ariADDna REST API v.1
REST API for this web service. For design API use Swagger specification. Last API version design save in [API](https://www.mindmeister.com/692033641).
* M1-1 - ariADDna REST API server.
* M1-2 - ariADDna client for ariADDna project.

### M2 - REST API Cloud Client
Functionality for connect to remote public cloud. Upload\download batch of file as binary files and tracking changes.
* M2-1 - REST API client for abstract cloud.
* M2-2 - Cloud Loader Service.
* M2-3 - Cloud Synchronization Service.
* M2-3 - Google Disk client specific cloud client.
* M2-4 - Yandex Disk client specific cloud client.
* M2-5 - Dropbox client specific cloud client.
* M2-6 - OneDrive client specific cloud client.
* M2-7 - Box client specific cloud client.


### M3 - GUI for desktop application
JavaFX implementing GUI for client desktop application.
* M3-1 - implementing View with Controllers (without any business logic or application lifecycle management logic)

### M4 - Web Admin Dashboard
Admin dashboard for manage ariADDna project.
* M4-1 - Single page web application (base on AngularJS).
* M4-2 - Integration with Grafana for import charts.

### M5 - Web UI
Web UI for client application. Base on [prototype](http://test.namars.ru/cloudraid/userpanel/home.html)
INFO: This application can contain not full functionality desktop application.
* M5-1 - Single page web application (base on AngularJS).
* M5-2 - Cryptographic module (investigation how we can use asymmetric keys in Web UI).

### M6 - Storage manager
Storage Manager for save system information. For database on desktop and server use H2.
* M6-1 - H2 in server mode.
* M6-2 - DAO Layer (implementing Hibernate Entities Layer and Repository pattern).
* M6-3 - DTO Layer (use MapStruct library implementing convert DTO to Entity and vice versa).

### M7 - Local File Storage System
Local File Storage System. System use for index, tracking and run CRUID operations on files.
* M7-1 - Filesystem Tracking Service
* M7-2 - Local Store Indexing Service

### M8 - Push Notification System
Push Notification Server and Client.
* M8-1 - Push Notification Server.
* M8-2 - Push Notification Client.

### M9 - Certificate Manager
Certificate Manage for manage JKS storage. JKS storage contains SSL certificates.
* M9-1 - store SSL certificate on JKS file.
* M9-2 - store asymmetric key on JKS file.

### M10 - VUFS 1.0
Virtual Union Filesystem release version 1.0.
* M10-1 - VUFS Snapshot Manager
* M10-2 - File Splitter Service
* M10-3 - VUFS Synchronization service
* M10-4 - Allocated Storage Service
* M10-5 - Balancer Service

### M11 - Server core
* M11-1 - server lifecycle management logic and common functionality (base exception, logging, performance statistic).

### M12 - Desktop application core
* M12-1 - application lifecycle management logic and common functionality (base exception, logging, performance statistic).

### M13 - Security Service
* M13-1 - User Authentication and Authorization Service in ariADDna web service.