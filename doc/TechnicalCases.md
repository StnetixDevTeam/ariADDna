# Technical cases

## #1: Send local changes with cloud storage

* _Tracking service_ find change file in filesystem
* _Tracking service_ make diff VUFS snapshot contain meta information about add, modify and remove files and catalog
* _Tracking service_ send diff VUFS snapshot to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ begin file change transaction on VUFS
* _VUFS Snapshot Manager_ add meta information about file change transaction to diff VUFS snapshot
* _VUFS Snapshot Manager_ send diff VUFS snapshot to remote VUFS Synchronization Service for get information about file distribution strategy (see: [Technical Case#2](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#2-client-vufs-snapshot-manager-send-diff-object-to-remote-vufs-synchronization-service-for-get-information-about-load-strategy))
* _VUFS Snapshot Manager_ send diff VUFS snapshot to File Splitter Service for get information about parts of files
* _VUFS Snapshot Manager_ send diff VUFS snapshot, parts of files, file distribution strategy to Cloud Loader Service
* _Cloud Loader Service_ upload parts of files to cloud shortages and save statistics
* _Cloud Loader Service_ return result statistics and result to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ send statistics and file change transaction result to remote VUFS Synchronization Service (see: [Technical Case#3](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#3-client-vufs-snapshot-manager-send-statistics-and-transaction-result-to-remote-vufs-synchronization-service))
* _VUFS Snapshot Manager_ commit file change transaction

## #2: Client VUFS Snapshot Manager send diff object to remote VUFS Synchronization Service for get information about load strategy

* _VUFS Snapshot Manager_ send diff VUFS snapshot to ariADDna Client
* _ariADDna Client_ convert internal diff VUFS snapshot to JSON diff VUFS snapshot for REST ariADDna API
* _ariADDna Client_ get credential (SSL certificate and HTTP token)
* _ariADDna Client_ send credential and JSON diff VUFS snapshot to REST client for send to remote REST server
* _REST Server_ check authenticate use Security Service
* _Security Service_ return REST server information about validation credential
* _REST Server_ call ariADDna Server component for convert diff VUFS snapshot (JSON object to internal format object)
* _ariADDna Server_ send diff VUFS snapshot to VUFS Synchronization Service
* _VUFS Synchronization Service_ calculate required storage space and allocation storage space use Allocation Storage Service
* _VUFS Synchronization Service_ get file distribution strategy use Balancer Service
* _VUFS Synchronization Service_ make meta information object (about file distribution strategy) and send to ariADDna Server
* _ariADDna Server_ convert internal meta info object to JSON format for send as responce
* _ariADDna Client_ receive responce over REST Client from REST Server
* _ariADDna Client_ convert JSON format to internal object format
* _ariADDna Client_ return meta information object (about file distribution strategy) to VUFS Snapshot Manager

## #3: Client VUFS Snapshot Manager send statistics and transaction result to remote VUFS Synchronization Service

* _VUFS Snapshot Manager_ send statistics and transaction result (commit or rollback) as meta information object to ariADDna Client
* _ariADDna Client_ convert internal meta information object to JSON meta information object for REST ariADDna API
* _ariADDna Client_ get credential (SSL certificate and HTTP token)
* _ariADDna Client_ send credential and JSON meta information object to REST client for send to remote REST server
* _REST server check_ authenticate use Security Service
* _Security service_ return REST server information about validation credential
* _REST Server_ call ariADDna Server component for convert meta information object (JSON object to internal format object)
* _ariADDna Server_ send meta information object to VUFS Synchronization Service
* _VUFS Synchronization Service_ commit file change transaction and save changed to database
* _VUFS Synchronization Service_ call ariADDna Server for return responce
* _ariADDna Server_ convert internal meta info object to JSON format for send as responce
* _ariADDna Client_ receive responce over REST Client from REST Server
* _ariADDna Client_ convert JSON format to internal object format
* _ariADDna Client_ call VUFS Snapshot Manager and send him responce

## #4: Synchronization local storage with cloud storage

* _Cloud Synchronization Service_ find change files in a cloud storage
* _Cloud Synchronization Service_ notify Synchronization Service about file in cloud storage was changed
* _VUFS Synchronization Service_ begin file change transaction
* _VUFS Synchronization Service_ make diff VUFS snapshot with current VUFS snapshot and save current snapshot in cache
* _VUFS Synchronization Service_ commit file change transaction
* _VUFS Synchronization Service_ call Push Notification Server for notify client devices (see: [Technical Case#5](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#5-send-push-notification-to-client))
* _Push Notification Client_ get notification about change VUFS snapshot
* _Push Notification Client_ notify VUFS Snapshot Manager about change file in cloud storage
* _VUFS Snapshot Manager_ send request for get remote VUFS snapshot over ariADDna Client
* _ariADDna Client_ send request over REST Client from REST Server for get remote VUFS snapshot (see: [Technical Case#8](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#8-get-remote-vufs-snapshot))
* _ariADDna Client_ get remote VUFS snapshot from remote VUFS Synchronization Service
* _ariADDna Client_ send remote VUFS snapshot to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ begin file change transaction
* _VUFS Snapshot Manager_ get current VUFS snapshot local file storage from Local File Storage Index Service
* _VUFS Snapshot Manager_ make diff VUFS snapshot for current VUFS snapshot and remote VUFS snapshot
* _VUFS Snapshot Manager_ get meta information about distributed file strategy per file from diff VUFS snapshot over ariADDna Client (see: [Technical Case#9](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#9-get-meta-information-about-distributed-file-strategy-per-file))
* _VUFS Snapshot Manager_ send diff VUFS snapshot, meta information about distributed file strategy per file to Cloud Loader Service
* _Cloud Loader Service_ download parts of files to cloud shortages and save statistics
* _Cloud Loader Service_ return result statistics and result to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ send statistics and file change transaction result to remote VUFS Synchronization Service (see: [Technical Case#3](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#3-client-vufs-snapshot-manager-send-statistics-and-transaction-result-to-remote-vufs-synchronization-service))
* _VUFS Snapshot Manager_ commit file change transaction

## #5: Send push notification to client

* _Server_ is run start Push Notification Server
* _Push Notification Server_ create notification queue
* _Some server component_ add notification message to notify queue
* _Application_ is run start Push Notification Client
* _Push Notification Client_ connect to Push Notification Server over ariADDna Client and get last notification (see: [Technical Case#10](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#10-get-last-notification-from-push-notification-server))
* _Push Notification Client_ notify to some recipient

## #6: First run client application with empty local storage

* _Application_ is run start Push Notification Client and other services
* _VUFS Snapshot Manager_ send request for get remote VUFS snapshot over ariADDna Client
* _ariADDna Client_ send request over REST Client from REST Server for get remote VUFS snapshot (see: [Technical Case#8](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#8-get-remote-vufs-snapshot))
* _ariADDna Client_ get remote VUFS snapshot from remote VUFS Synchronization Service
* _ariADDna Client_ send remote VUFS snapshot to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ begin file change transaction
* _VUFS Snapshot Manager_ get current VUFS snapshot local file storage from Local File Storage Index Service
* _VUFS Snapshot Manager_ make diff VUFS snapshot for current VUFS snapshot and remote VUFS snapshot
* _VUFS Snapshot Manager_ get meta information about distributed file strategy per file from diff VUFS snapshot over ariADDna Client (see: [Technical Case#9](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#9-get-meta-information-about-distributed-file-strategy-per-file))
* _VUFS Snapshot Manager_ send diff VUFS snapshot, meta information about distributed file strategy per file to Cloud Loader Service
* _Cloud Loader Service_ download parts of files to cloud shortages and save statistics
* _Cloud Loader Service_ return result statistics and result to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ send statistics and file change transaction result to remote VUFS Synchronization Service (see: [Technical Case#3](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#3-client-vufs-snapshot-manager-send-statistics-and-transaction-result-to-remote-vufs-synchronization-service))
* _VUFS Snapshot Manager_ commit file change transaction

## #7: First run client application with local storage contains some files

* _Application_ is run start Push Notification Client and other services
* _VUFS Snapshot Manager_ send request for get remote VUFS snapshot over ariADDna Client
* _ariADDna Client_ send request over REST Client from REST Server for get remote VUFS snapshot (see: [Technical Case#8](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#8-get-remote-vufs-snapshot))
* _ariADDna Client_ get remote VUFS snapshot from remote VUFS Synchronization Service
* _ariADDna Client_ send remote VUFS snapshot to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ begin file change transaction
* _VUFS Snapshot Manager_ get current VUFS snapshot local file storage from Local File Storage Index Service
* _VUFS Snapshot Manager_ make diff VUFS snapshot for current VUFS snapshot and remote VUFS snapshot
* _VUFS Snapshot Manager_ if files has conflict local files move to folder "please_fix_conflict-YYYY-MM-DD"
* _VUFS Snapshot Manager_ get meta information about distributed file strategy per file from diff VUFS snapshot over ariADDna Client (see: [Technical Case#9](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#9-get-meta-information-about-distributed-file-strategy-per-file))
* _VUFS Snapshot Manager_ send diff VUFS snapshot, meta information about distributed file strategy per file to Cloud Loader Service
* _Cloud Loader Service_ download parts of files to cloud shortages and save statistics
* _Cloud Loader Service_ return result statistics and result to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ send statistics and file change transaction result to remote VUFS Synchronization Service (see: [Technical Case#3](https://github.com/StnetixDevTeam/ariADDna/blob/master/doc/TechnicalCases.md#3-client-vufs-snapshot-manager-send-statistics-and-transaction-result-to-remote-vufs-synchronization-service))
* _VUFS Snapshot Manager_ commit file change transaction

## #8: Get remote VUFS snapshot

* _VUFS Snapshot Manager_ connect to remote VUFS Synchronization Service over ariADDna Client
* _ariADDna Client_ convert internal request object to JSON request object for REST ariADDna API
* _ariADDna Client_ get credential (SSL certificate and HTTP token)
* _ariADDna Client_ send credential and JSON request object to REST client for send to remote REST server
* _REST server check_ authenticate use Security Service
* _Security service_ return REST server information about validation credential
* _REST Server_ call ariADDna Server component for convert request object (JSON object to internal format object)
* _ariADDna Server_ send request object to VUFS Synchronization Service
* _VUFS Synchronization Service_ make last VUFS snapshot and send to ariADDna Server
* _ariADDna Server_ convert internal last VUFS snapshot to JSON format for send as responce
* _ariADDna Client_ receive responce over REST Client from REST Server
* _ariADDna Client_ convert JSON format to internal object format and sen to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ get remote VUFS snapshot

## #9: Get meta information about distributed file strategy per file

* _VUFS Snapshot Manager_ connect to remote VUFS Synchronization Service over ariADDna Client
* _ariADDna Client_ convert internal meta information about distributed file strategy per file object to JSON request object for REST ariADDna API
* _ariADDna Client_ get credential (SSL certificate and HTTP token)
* _ariADDna Client_ send credential and JSON meta information about distributed file strategy per file object to REST client for send to remote REST server
* _REST server check_ authenticate use Security Service
* _Security service_ return REST server information about validation credential
* _REST Server_ call ariADDna Server component for convert meta information about distributed file strategy per file object (JSON object to internal format object)
* _ariADDna Server_ send meta information about distributed file strategy per file object to VUFS Synchronization Service
* _VUFS Synchronization Service_ make last VUFS snapshot and send to ariADDna Server
* _VUFS Synchronization Service_ send meta information about distributed file strategy per file object to Balancer Service
* _Balancer Service_ send initialized meta information about distributed file strategy per file object to VUFS Synchronization Service
* _ariADDna Server_ convert internal meta information about distributed file strategy per file object to JSON format for send as responce
* _ariADDna Client_ receive responce over REST Client from REST Server
* _ariADDna Client_ convert JSON format to internal object format and sen to VUFS Snapshot Manager
* _VUFS Snapshot Manager_ get meta information about distributed file strategy per file object

## #10: Get last notification from Push Notification Server

* _Push Notification Client_ connect to remote Push Notification Server over ariADDna Client and get last notification
* _ariADDna Client_ convert internal request object to JSON request object for REST ariADDna API
* _ariADDna Client_ get credential (SSL certificate and HTTP token)
* _ariADDna Client_ send credential and JSON request object to REST client for send to remote REST server
* _REST server check_ authenticate use Security Service
* _Security service_ return REST server information about validation credential
* _REST Server_ call ariADDna Server component for convert request object (JSON object to internal format object)
* _ariADDna Server_ send request object to Push Notification Server
* _Push Notification Server_ send last notification to ariADDna Server
* _ariADDna Server_ convert internal last notification object to JSON format for send as responce
* _ariADDna Client_ receive responce over REST Client from REST Server
* _ariADDna Client_ convert JSON format to internal object format and sen to Push Notification Client
* _Push Notification Client_ send notification to recipient

## #11: Detecting unavailable cloud storage

## #12: Rebalanced cloud storage

## #13: Add new user on ariADDna service

## #14: Delete user on ariADDna service

## #15: Lock user on ariADDna service

## #16: Unlock user on ariADDna service

## #17: Set meta information about distributed storage strategy

## #18: Set public key for encryption part of file

## #14: Import current exists files on cloud storage

