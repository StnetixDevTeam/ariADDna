# ariADDna

Single point of control some clouds providers GoogleDrive, iCloud, DropBox, Box, OneDrive in version 1.0. and Amazon S3, Azure, OpenStack Swift in version 2.0.

User can set of resource utilization strategy (space available for file storage). You can choose of resource utilization strategies: mirroring of files, the union of the space, clouds priority for the use and combination of these strategies. So you can encrypt your files use asymmetric keys.

Desktop applications installed on your computer (Windows, Mac OS X, Linux) version 1.0 to automatically synchronize selected folders with clouds. So on mobile application in version 2.0 or some other your computer you can select some others folder for synchronization.

## Application Architecture

![application architecture ariADDna service](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/images/Aplication%20Architecture%20ariADDna.png)

On this image you see links:
* 1 - request from client to synchronization server for get structure or last state virtual union file system, load metrics and get other system specific information (change user profile, keys, utilization strategy),
* 2 - request from client to cloud provider for put or get files,
* 3 - request from web client to synchronization server for setting some system specific information,
* 4 - optional request from synchronization server to cloud provider for make consist state virtual union file system.

## Desktop Client Application Architecture

![application architecture ariADDna service](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/images/Client%20Architecture.png)

Application contains main parts:
* GUI - JavaFX view forms and controllers
* Application - main service run and initialized all service for correct working desktop application client
* Common services - service Push Notification Service, Store Manager (DAO providers for connect to database), Certificate Manager (service for save SSL certificates and private keys in JKS storage)
* VUFS - services for working Virtual Union Filesystem
* Local Filesystem services - services for working local filesystem: tracking, indexing.
* REST services - services for working with remote webservice

## Server Architecture

![server architecture ariADDna service](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/images/Server%20Architecture.png)

Server contains main parts:
* WebUI - contains two part Web Client for minimal functionality and Admin Dashboard
* Server - main service run and initialized all service for correct working server
* Common services - service Push Notification Service, Store Manager (DAO providers for connect to database), Certificate Manager (service for save SSL certificates and private keys in JKS storage), Security Service(check authentication)
* VUFS - services for working Virtual Union Filesystem
* REST services - services for working with remote webservice and client application

## Virtual Union Filesystem.

Virtual Union Filesystem (VUFS) - it is distribution manager. This manager contains indexes and meta information about distribution parts for storage of files. Manage file synchronization on all devices connected to ariADDna service.
VUFS Snapshot - current state VUFS, contains list of all exists files and meta information (ACL, properties and other system information).

## Technical cases

See [technical cases](https://github.com/StnetixDevTeam/ariADDna/tree/master/doc/TechnicalCases.md)