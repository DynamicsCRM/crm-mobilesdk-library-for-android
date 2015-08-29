# Microsoft Dynamics CRM Mobile SDK for Android (Java)
-------------------------------------------------

This document describes the Java version of the Dynamics CRM Mobile SDK that is being released under an open source license on [GitHub.com](http://github.com/dynamicscrm). This SDK provides service proxy classes and message classes that enable you to develop Android mobile applications that can connect to the CRM organization web service and perform various operations. The supported messages in this SDK, that are defined later in this document, represents a usable subset of what is available in the .NET version of the CRM SDK. In general, it is best to be familiar with the .NET version of the [CRM SDK](https://msdn.microsoft.com/en-us/library/gg309408.aspx) as the programming API in this mobile SDK was based on it. The following documentation describes the key features and API available in this SDK.

[OAuth](http://oauth.net/2/) authentication with the Dynamics CRM web service is not managed within this SDK. That functionality is handled in app level code using the [Azure Active Directory Authentication Library](https://github.com/AzureAD/azure-activedirectory-library-for-java) (ADAL). For an example of using this SDK in a real world Android application, complete with OAuth web service authentication, refer to the sample Android app that is provided as a separate download.

## Requirements ##

This SDK supports and has been tested with the following development environment.

* Android API level 18 through 23
* Android Studio version 1.0 and later

At build time, the following open source or 3rd party libraries are downloaded and installed, which require and active internet connection on the development computer.

* [Azure Active Directory Authentication Library](https://github.com/AzureAD/azure-activedirectory-library-for-android)
* [Square's Retrofit](http://square.github.io/retrofit/)
* [Apache Commons](http://commons.apache.org/)

This SDK is known to work with Dynamics CRM 2013 and 2015, for both on-premises and Online deployments. On-premises identity authentication is provided by Active Directory Federation Services (3.0 or greater) while Online identity authentication is provided by Microsoft Azure Active Directory.

## Getting Started

Prior to using the methods provided by this mobile SDK, you must build and run the (.NET) *MobileSdkGen* command line tool to generate early-bound classes for each entity in the CRM organization that your app needs to access. The tool plus related documentation is provided as a separate download. This tool is similar to the *CrmSvcUtil* tool provided in the .NET version of the CRM SDK. After running the MobileSdkGen tool, the result is a folder filled with .java class files, one for each entity type (including custom entity types) and option sets that you have specified.

For more information about using early-bound entity types, refer to the related CRM SDK topic [Use the early bound entity classes in code](https://msdn.microsoft.com/en-us/library/gg328210.aspx). For more information about the CrmSvcUtil tool, see [Create early bound entity classes with the code generation tool](https://goo.gl/QhikBZ).

To include this library into your project, you should just take a built *.aar file from the releases section, or from the build folder of this project. You should place that file into your lib folder for you application and include the following into your application's `gradle.build` file:

```groovy
compile(name: 'crmsdk2015', ext: 'aar') {
    transitive = true;
}
```

Also keep in mind that if you are using Proguard you should add this to your `proguard-rules.pro` file:

```proguard
-keep class * extends com.microsoft.xrm.sdk.Entity { *; }
```

## Web Service Proxies

There are two web service proxy classes provided in this mobile SDK: `OrganizationServiceProxy`, and `RestOrganizationServiceProxy`. It is through the methods of these proxies that you send message requests to the organization web service and receive responses.

```java
public OrganizationServiceProxy(String uri, String sessionToken)
```

```java
public RestOrganizationServiceProxy(String uri, RequestInterceptor authHeader)
```

Creating either of these proxy objects requires you to manage user logon using Active Directory and pass into the constructor either the authentication access token or a pre-built header. `RequestInterceptor` is a header system that you can create using the Retrofit library. The URI value in these constructors is the SSL (https://) address of your CRM organization's web service (Organization.svc). This address can be found in the CRM web application by navigating to **Settings** > **Customizations** > **Developer Resources**.

The reason for two proxies is that `OrganizationServiceProxy` supports sending SOAP messages while `RestOrganizationServiceProxy` supports sending OData messages. Most messages supported by CRM are implemented as SOAP messages. A small subset of messages are supported by the OData v2 endpoint. For more information on the OData v2 endpoint, see [Use web service data in web resources (OData and Modern app SOAP endpoint)](https://msdn.microsoft.com/en-us/library/gg490659.aspx).

## RestOrganizationServiceProxy Methods
The following sections describe the available methods of the OData based service proxy.

Each proxy method takes a `Callback` class instance as a parameter. The `Callback` class defines two methods named `success` and `failure`. One of these methods is called after the web service attempts to perform the intended operation. It is through the `success` callback that you can obtain the response.

### Create

Creates an entity record.

```java
void Create(Entity entity, Callback<UUID> callback)

void Create(Entity relatedTo, Entity entity, String relationshipName, Callback<UUID> callback)
```

For the `Entity` instance provided to the method to be serialized correctly, it must be an early-bound subclass of the `Entity` class provided in the mobile SDK. You can generate these classes using the MobileSdkGen tool. In the example below, the `Contact` class was generated using this tool. However, if you are using `Create` to make a related entity it does let you pass in the second entity even if it isn't a subclass of `Entity`. It will of course throw a network error if you don't use proper schema names for the attributes.

```java
Contact contact = Contact.build()
        .setFirstName("John")
        .setLastName("Doe")
        .setNumberOfChildren(2);

try
{
    restService.Create(contact, new Callback<UUID>() {
        @Override
        public void success(UUID uuid, Response response) {
            // id of the new contact
        }

        @Override
        public void failure(RetrofitError error) {
            // handle network error
        }
    });
}
catch(InvalidClassException ex) {
    // didn't pass in a subclass of entity
}
```


### Update

Updates an existing entity record.

```java
void Update(Entity entity, @Nullable Callback<?> callback)
```

For the `Entity` instance provided to the method to be serialized correctly, it must be an early-bound subclass of the `Entity` class provided in the mobile SDK. You can generate these classes using the MobileSdkGen tool. In the example below, the `Contact` class was generated using this tool. For an update, the ID of the existing record must be supplied. The callback is nullable so that you can either ignore it or, if you like, create one to handle any network failures coming back.

```java
Contact contact = Contact.build()
        .setContactId(UUID.fromString("ab4db725-0aab-45c9-a23d-d7a865635974"))
        .setNumberOfChildren(3);

try {
    restService.Update(contact, new Callback<Object>() {
        @Override
        public void success(Object object, Response response) {
            // this will never come back
        }

        @Override
        public void failure(RetrofitError error) {
            // handle network error
        }
    });
}
catch(InvalidClassException ex) {
    // didn't pass in a subclass of entity
}
```

### Delete

Deletes an existing entity record.

```java
void Delete(String entitySchemaName, UUID id, @Nullable Callback<?> callback);
```

```java
restService.Delete("Contact", UUID.fromString("ab4db725-0aab-45c9-a23d-d7a865635974"), null);
```

### Retrieve

Two methods are provided to retrieve records: one to retrieve a single record, and another to retrieve all records of the given type to which the user has read access, or get all related items to a specific entity. Filtering is possible with the OData endpoint using the expand, filter, orderby, select, skip, and top parameters from `QueryOptions`.

These calls return generic entities and should be cast to early-bound entities using the `.toEntity(Class<T>)` method.

```java
void RetrieveMultiple(String entitySchemaName, UUID id, String relationshipName, @NonNull QueryOptions queryOptions, final Callback<EntityCollection> callback);

void RetrieveMultiple(String entitySchemaName, QueryOptions query, Callback<EntityCollection> callback);
```

```java
// Retrieve the top ten activities that have an end date and are related to the contact.
QueryOptions queryOptions = QueryOptions.build()
        .putTop("10")
        .putSelect(Constants.ACTIVITY_SELECT)
        .putFilter("ActualEnd ne null")
        .putOrderBy("ActualEnd desc");

restService.RetrieveMultiple(Contact.class.getSimpleName(), mContact.getId(), "Contact_ActivityPointers",
        queryOptions, new Callback<EntityCollection>() {
            @Override
            public void success(EntityCollection entityCollection, Response response) {
                // handle response
            }

            @Override
            public void failure(RetrofitError error) {
                //handle network error
            }
        });
```

## OrganizationServiceProxy Methods
The following sections describe the available methods of the service proxy that sends SOAP based messages to the organization web service.

Each proxy method takes a `Callback` class instance as a parameter. The `Callback` class defines two methods named `success` and `failure`. One of these methods is called after the web service attempts to perform the intended operation. It is through the `success` callback that you can obtain the response.

### Execute
The `Execute` method sends a message request to the organization web service and receives a response back. The message request and response classes, listed later in this document, are derived from `OrganizationRequest` and `OrganizationResponse`. There are many more messages available to execute than there are methods on the proxy class. Where there is overlap, for example a Create message and a Create method, you can choose to use either. There is not a significant performance difference between the two techniques.

```java
void Execute(OrganizationRequest request, Callback<OrganizationResponse> callback);
```

The mobile SDK provides a wide range of message request and response classes for you to use with the `Execute` proxy method. For example:

```java
SetStateRequest setStateRequest = SetStateRequest.build()
        .setEntityMoniker(new EntityReference("task", activityId))
        .setState(new OptionSetValue(1))
        .setStatus(new OptionSetValue(5));

mOrgService.Execute(setStateRequest, new Callback<OrganizationResponse>() {
    @Override
    public void success(OrganizationResponse organizationResponse, Response response) {
        //handle response
    }

    @Override
    public void failure(RetrofitError error) {
        //handle error
    }
});
```

### Create

Creates an entity record. This is equivalent to using a `CreateRequest` with the `Execute` method.

```java
void Create(Entity entity, Callback<UUID> callback);
```

### Update

Updates an existing entity record. This is equivalent to using an `UpdateRequest` with the `Execute` method.

```java
void Update(Entity entity, @Nullable Callback<?> callback);
```

For this method you have the option to use the callback. The `success` method of the callback class will never get called, however, you will get any network failures back through the callback's `failure` method.

### Delete

Deletes an existing entity record. This is equivalent to using an `DeleteRequest` with the `Execute` method.

```java
void Delete(String entityName, UUID id, @Nullable Callback<?> callback);
```

For this request you have the option to use the callback. The `success` method of the callback class will never get called, however, you will get any network failures back through the callback's `failure` method.

### Retrieve

Retrieves an existing entity record. This is equivalent to using a `RetrieveRequest` with the `Execute` method.

```java
void Retrieve(String entitySchemaName, UUID id, @NonNull ColumnSet columnSet, Callback<Entity> callback);
```

The column-set defines the attributes that you want returned in the entity record.

### RetrieveMultiple

Retrieves multiple entity records. This method is equivalent to using a `RetrieveMultipleRequest` with the `Execute` method. One thing that is unique about the SOAP version of `RetrieveMultiple` compared to the OData version is that both the method and the message request support FetchXML for the query.

```java
void RetrieveMultiple(QueryBase query, Callback<EntityCollection> callback);
```

```java
FetchExpression fetchExpression = new FetchExpression(String.format(
    "<fetch mapping='logical' count='25'>" +
        "<entity name='contact'>" +
            "<attribute name='contactid'/>" +
            "<attribute name='fullname'/>" +
            "<attribute name='jobtitle'/>" +
            "<link-entity name='account' from='accountid' to='parentcustomerid' link-type='outer'>" +
                "<attribute name='name' alias='accountname' />" +
            "</link-entity>" +
            "<filter type='and'>" +
                "<condition attribute='fullname' operator='like' value='%%%s%%' />" +
            "</filter>" +
        "</entity>" +
    "</fetch>", searchTerm));

mOrgService.RetrieveMultiple(fetchExpression, new Callback<EntityCollection>() {
    @Override
    public void success(EntityCollection entityCollection, Response response) {
        // handle response
    }

    @Override
    public void failure(RetrofitError error) {
        // handle network error
    }
});
```

For more information about FetchXML see [Build queries with FetchXML](https://msdn.microsoft.com/en-us/library/gg328332.aspx).

### Associate

Creates a link between records that participate in a relationship. This is equivalent to using an `AssociateRequest` with the `Execute` method.

```java
void Associate(String entityName, UUID entityId, Relationship relationship, EntityReferenceCollection relatedEntities, @Nullable Callback<?> callback);
```

### Disassociate

Removes the link between records. This is equivalent to using an `DisassociateRequest` with the `Execute` method.

```java
void Disassociate(String entityName, UUID entityId, Relationship relationship, EntityReferenceCollection relatedEntities, @Nullable Callback<?> callback);
```

## Supported Organization Web Service (SOAP) Messages
For each of the messages listed below, there exists a request and response class. For example, the Create message includes `CreateRequest` and `CreateResponse` classes. These classes can be found in the folder mobilesdk-android\crmsdk2015\src\main\java\com\microsoft\xrm\sdk\Messages.

For more information about each of these messages, see [CRM messages in the organization service](https://msdn.microsoft.com/en-us/library/gg309482.aspx) and [xRM messages in the Organization service](https://msdn.microsoft.com/en-us/library/gg334698.aspx).

**A**: AddItemCampaign,
AddListMembersList,
AddMemberList,
AddPrincipalToQueue,
AddProductToKit,
AddRecurrence,
AddToQueue,
Assign,
AssociateEntities,
Associate

**B**: BackgroundSendEmail,
Book

**C**: CanBeReferenced,
CanBeReferencing,
CancelContract,
CancelSalesOrder,
CanManyToMany,
CheckIncomingEmail,
CheckPromoteEmail
CloneContract,
CloseIncident,
CloseQuote,
CopyDynamicListToStatic,
CreateEntity,
CreateException,
CreateInstance,
CreateOptionSet,
Create

**D**: DeleteAttribute,
DeleteEntity,
DeleteOpenInstances,
DeleteOptionSet,
DeleteOptionValue,
DeleteRelationship,
Delete,
DeliverIncomingEmail,
DeliverPromoteEmail,
DisassociateEntities,
Disassociate

**G**: GetValidManyToMany,
GetValidReferencedEntities,
GetValidReferencingEntities

**I**: InsertOptionValue,
InsertStatusValue,
IsDataEncryptionActive

**L**: LockInvoicePricing,
LockSalesOrderPricing,
LoseOpportunity

**M**: Merge

**O**: OrderOption

**P**: PickFromQueue

**Q**: QualifyLead

**R**: Recalculate,
ReleaseToQueue,
RemoveFromQueue,
RemoveItemCampaign,
RemoveMemberList,
RemovePrivilegeRole,
RemoveRelated,
ReplacePrivilegesRole,
Reschedule,
RetrieveAllEntities,
RetrieveAllManagedProperties,
RetrieveAllOptionSets,
RetrieveAttribute,
RetrieveDataEncryptionKey,
RetrieveEntity,
RetrieveManagedProperty,
RetrieveMetadataChanges,
RetrieveMultiple,
RetrieveOptionSet,
RetrieveRelationship,
Retrieve,
RetrieveTimestamp,
RetrieveUserQueues,
RouteTo

**S**: SendEmail,
SendFax,
SetDataEncryptionKey,
SetRelated,
SetState

**U**: UpdateAttribute,
UpdateOptionSet,
UpdateOptionValue,
UpdateRelationship,
Update,
UpdateStateValue

**V**: ValidateRecurrenceRule

**W**: WinOpportunity,
WinQuote
