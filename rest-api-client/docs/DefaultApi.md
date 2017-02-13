# DefaultApi

All URIs are relative to *https://api.ariaddna.stnetix.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addExternalCloudAccount**](DefaultApi.md#addExternalCloudAccount) | **POST** /clouds | 
[**addUser**](DefaultApi.md#addUser) | **POST** /users | 
[**authUser**](DefaultApi.md#authUser) | **POST** /auth | 
[**changeUserPassword**](DefaultApi.md#changeUserPassword) | **PUT** /users/{userUuid} | 
[**deleteExternalCloudAccount**](DefaultApi.md#deleteExternalCloudAccount) | **DELETE** /clouds/{cloudUuid} | 
[**deleteUser**](DefaultApi.md#deleteUser) | **DELETE** /users/{userUuid} | 
[**findUserByUuid**](DefaultApi.md#findUserByUuid) | **GET** /users/{userUuid} | 
[**getCloudStatisticSet**](DefaultApi.md#getCloudStatisticSet) | **GET** /stat/vufs/{userUuid} | 
[**getDiffVUFS**](DefaultApi.md#getDiffVUFS) | **GET** /vufs/snap/diff/{userUuid}/{dateTime} | 
[**getExternalCloudAccounts**](DefaultApi.md#getExternalCloudAccounts) | **GET** /users/{userUuid}/clouds | 
[**getHealthCheckStat**](DefaultApi.md#getHealthCheckStat) | **GET** /stat/vufs/health/{userUuid} | 
[**getVUFS**](DefaultApi.md#getVUFS) | **GET** /vufs/snap/{userUuid} | 
[**logoutSession**](DefaultApi.md#logoutSession) | **PUT** /auth/{uuid} | 
[**postAllocateModel**](DefaultApi.md#postAllocateModel) | **POST** /vufs/allocation/{userUuid} | 
[**postCloudStatSet**](DefaultApi.md#postCloudStatSet) | **POST** /stat/vufs/{userUuid} | 
[**sendChangesInLFS**](DefaultApi.md#sendChangesInLFS) | **POST** /vufs/snap/diff/{userUuid} | 


<a name="addExternalCloudAccount"></a>
# **addExternalCloudAccount**
> Cloud addExternalCloudAccount(cloud)



Allows one to add an external cloud account to an existing ariADDna&#39;s user. The User MUST be already registered at the cloud service to be added.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Cloud cloud = new Cloud(); // Cloud | A required information about an external cloud service that a user wants to include to his ariADDna.
try {
    Cloud result = apiInstance.addExternalCloudAccount(cloud);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addExternalCloudAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **cloud** | [**Cloud**](Cloud.md)| A required information about an external cloud service that a user wants to include to his ariADDna. | [optional]

### Return type

[**Cloud**](Cloud.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addUser"></a>
# **addUser**
> User addUser(user)



Allows one to create a new user.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
User user = new User(); // User | A new User-object containing all specific information that makes user profile unique.
try {
    User result = apiInstance.addUser(user);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | [**User**](User.md)| A new User-object containing all specific information that makes user profile unique. |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="authUser"></a>
# **authUser**
> Session authUser(user)



Creating new user session.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Credential user = new Credential(); // Credential | Authorization user credential.
try {
    Session result = apiInstance.authUser(user);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#authUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | [**Credential**](Credential.md)| Authorization user credential. |

### Return type

[**Session**](Session.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="changeUserPassword"></a>
# **changeUserPassword**
> changeUserPassword(userUuid, user)



Changing user password.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | UUID of user to fetch.
User user = new User(); // User | Changed user.
try {
    apiInstance.changeUserPassword(userUuid, user);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#changeUserPassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| UUID of user to fetch. |
 **user** | [**User**](User.md)| Changed user. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteExternalCloudAccount"></a>
# **deleteExternalCloudAccount**
> String deleteExternalCloudAccount(cloudUuid)



Allows to delete user&#39;s external cloud account.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String cloudUuid = "cloudUuid_example"; // String | An external cloud identifier which user has inside his profile. Allows to delete external cloud profile.
try {
    String result = apiInstance.deleteExternalCloudAccount(cloudUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteExternalCloudAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **cloudUuid** | **String**| An external cloud identifier which user has inside his profile. Allows to delete external cloud profile. |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteUser"></a>
# **deleteUser**
> deleteUser(userUuid)



Deleting user.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | UUID of user to delete.
try {
    apiInstance.deleteUser(userUuid);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| UUID of user to delete. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findUserByUuid"></a>
# **findUserByUuid**
> User findUserByUuid(userUuid)



This operation allows one to get back information about certain user providing his UUID as a path parameter.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | The UUID of a user to fetch.
try {
    User result = apiInstance.findUserByUuid(userUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findUserByUuid");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| The UUID of a user to fetch. |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getCloudStatisticSet"></a>
# **getCloudStatisticSet**
> StatisticSet getCloudStatisticSet(userUuid)



Allows to get statistic object about clouds.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | User UUID.
try {
    StatisticSet result = apiInstance.getCloudStatisticSet(userUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getCloudStatisticSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| User UUID. |

### Return type

[**StatisticSet**](StatisticSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDiffVUFS"></a>
# **getDiffVUFS**
> Vufs getDiffVUFS(userUuid, dateTime)



Allows to get difference of previous snapshot and actual.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | Current user UUID.
Long dateTime = 789L; // Long | FromDateTime of current VUFS snapshot
try {
    Vufs result = apiInstance.getDiffVUFS(userUuid, dateTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getDiffVUFS");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| Current user UUID. |
 **dateTime** | **Long**| FromDateTime of current VUFS snapshot |

### Return type

[**Vufs**](Vufs.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getExternalCloudAccounts"></a>
# **getExternalCloudAccounts**
> CloudSetPages getExternalCloudAccounts(userUuid)



Returns a list of clouds which a certain user has connected to his ariADDna account.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | Providing user's UUID one can access his CloudSet and add retrieve the list of available external cloud accounts.
try {
    CloudSetPages result = apiInstance.getExternalCloudAccounts(userUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getExternalCloudAccounts");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| Providing user&#39;s UUID one can access his CloudSet and add retrieve the list of available external cloud accounts. |

### Return type

[**CloudSetPages**](CloudSetPages.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getHealthCheckStat"></a>
# **getHealthCheckStat**
> StatisticSet getHealthCheckStat(userUuid)



Allows to get health-check statistic about users Clouds.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | User UUID.
try {
    StatisticSet result = apiInstance.getHealthCheckStat(userUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getHealthCheckStat");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| User UUID. |

### Return type

[**StatisticSet**](StatisticSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getVUFS"></a>
# **getVUFS**
> Vufs getVUFS(userUuid)



Allows to get snapshot vufs.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | Current user UUID.
try {
    Vufs result = apiInstance.getVUFS(userUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getVUFS");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| Current user UUID. |

### Return type

[**Vufs**](Vufs.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="logoutSession"></a>
# **logoutSession**
> logoutSession(uuid)



Closing user session.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String uuid = "uuid_example"; // String | UUID of user session.
try {
    apiInstance.logoutSession(uuid);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#logoutSession");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uuid** | **String**| UUID of user session. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postAllocateModel"></a>
# **postAllocateModel**
> Vufs postAllocateModel(userUuid, initialAllocationModel)



Allows to post file allocate strategy from client to server.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | User UUID.
InitialAllocationModel initialAllocationModel = new InitialAllocationModel(); // InitialAllocationModel | File allocation strategy.
try {
    Vufs result = apiInstance.postAllocateModel(userUuid, initialAllocationModel);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#postAllocateModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| User UUID. |
 **initialAllocationModel** | [**InitialAllocationModel**](InitialAllocationModel.md)| File allocation strategy. |

### Return type

[**Vufs**](Vufs.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postCloudStatSet"></a>
# **postCloudStatSet**
> postCloudStatSet(userUuid, cloudStatisticSet)



Allows to post statistic from client to server about clouds.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String userUuid = "userUuid_example"; // String | User UUID.
StatisticSet cloudStatisticSet = new StatisticSet(); // StatisticSet | Cloud statistic set typed object.
try {
    apiInstance.postCloudStatSet(userUuid, cloudStatisticSet);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#postCloudStatSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userUuid** | **String**| User UUID. |
 **cloudStatisticSet** | [**StatisticSet**](StatisticSet.md)| Cloud statistic set typed object. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="sendChangesInLFS"></a>
# **sendChangesInLFS**
> Vufs sendChangesInLFS(localChanges, userUuid)



Allows to send changes in local file storage to server with empty Allocation model and as response get Vufs object with Allocation model.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Vufs localChanges = new Vufs(); // Vufs | VUFS snapshot typed object with changed in local file storage with empty Allocation model.
String userUuid = "userUuid_example"; // String | Current user UUID.
try {
    Vufs result = apiInstance.sendChangesInLFS(localChanges, userUuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#sendChangesInLFS");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **localChanges** | [**Vufs**](Vufs.md)| VUFS snapshot typed object with changed in local file storage with empty Allocation model. |
 **userUuid** | **String**| Current user UUID. |

### Return type

[**Vufs**](Vufs.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

