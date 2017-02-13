# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        Cloud cloud = new Cloud(); // Cloud | A required information about an external cloud service that a user wants to include to his ariADDna.
        try {
            Cloud result = apiInstance.addExternalCloudAccount(cloud);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#addExternalCloudAccount");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://api.ariaddna.stnetix.com/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**addExternalCloudAccount**](docs/DefaultApi.md#addExternalCloudAccount) | **POST** /clouds | 
*DefaultApi* | [**addUser**](docs/DefaultApi.md#addUser) | **POST** /users | 
*DefaultApi* | [**authUser**](docs/DefaultApi.md#authUser) | **POST** /auth | 
*DefaultApi* | [**changeUserPassword**](docs/DefaultApi.md#changeUserPassword) | **PUT** /users/{userUuid} | 
*DefaultApi* | [**deleteExternalCloudAccount**](docs/DefaultApi.md#deleteExternalCloudAccount) | **DELETE** /clouds/{cloudUuid} | 
*DefaultApi* | [**deleteUser**](docs/DefaultApi.md#deleteUser) | **DELETE** /users/{userUuid} | 
*DefaultApi* | [**findUserByUuid**](docs/DefaultApi.md#findUserByUuid) | **GET** /users/{userUuid} | 
*DefaultApi* | [**getCloudStatisticSet**](docs/DefaultApi.md#getCloudStatisticSet) | **GET** /stat/vufs/{userUuid} | 
*DefaultApi* | [**getDiffVUFS**](docs/DefaultApi.md#getDiffVUFS) | **GET** /vufs/snap/diff/{userUuid}/{lastCreationTime} | 
*DefaultApi* | [**getExternalCloudAccounts**](docs/DefaultApi.md#getExternalCloudAccounts) | **GET** /users/{userUuid}/clouds | 
*DefaultApi* | [**getHealthCheckStat**](docs/DefaultApi.md#getHealthCheckStat) | **GET** /stat/vufs/health/{userUuid} | 
*DefaultApi* | [**getVUFS**](docs/DefaultApi.md#getVUFS) | **GET** /vufs/snap/{userUuid} | 
*DefaultApi* | [**logoutSession**](docs/DefaultApi.md#logoutSession) | **PUT** /auth/{uuid} | 
*DefaultApi* | [**postAllocateModel**](docs/DefaultApi.md#postAllocateModel) | **POST** /vufs/allocation/{userUuid} | 
*DefaultApi* | [**postCloudStatSet**](docs/DefaultApi.md#postCloudStatSet) | **POST** /stat/vufs/{userUuid} | 
*DefaultApi* | [**sendChangesInLFS**](docs/DefaultApi.md#sendChangesInLFS) | **POST** /vufs/snap/diff/{userUuid} | 


## Documentation for Models

 - [AllocationModel](docs/AllocationModel.md)
 - [AriaddnaObj](docs/AriaddnaObj.md)
 - [Cloud](docs/Cloud.md)
 - [CloudAccessToken](docs/CloudAccessToken.md)
 - [CloudSet](docs/CloudSet.md)
 - [CloudSetPages](docs/CloudSetPages.md)
 - [Credential](docs/Credential.md)
 - [ErrorModel](docs/ErrorModel.md)
 - [InitialAllocationModel](docs/InitialAllocationModel.md)
 - [PartLocation](docs/PartLocation.md)
 - [Property](docs/Property.md)
 - [Session](docs/Session.md)
 - [Statistic](docs/Statistic.md)
 - [StatisticSet](docs/StatisticSet.md)
 - [User](docs/User.md)
 - [Vufs](docs/Vufs.md)
 - [VufsFile](docs/VufsFile.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

ariaddna.support@stnetix.com

