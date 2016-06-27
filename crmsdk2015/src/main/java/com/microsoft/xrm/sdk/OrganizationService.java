package com.microsoft.xrm.sdk;

import android.support.annotation.NonNull;

import com.microsoft.xrm.sdk.Query.QueryBase;

import java.util.UUID;

import rx.Observable;

public interface OrganizationService {
    /**
     * Creates a record.
     * @param entity An entity instance that contains the properties to set in the newly created record.
     */
    Observable<UUID> Create(@NonNull Entity entity);

    /**
     * Creates a record
     * @param entity An entity instance that contains the properties to set in the newly created record.
     * @param callback The callback interface that will be call on complete (use if you don't want to use RXJava)
     */
    void Create(@NonNull Entity entity, @NonNull Callback<UUID> callback);

    /**
     * Deletes a record.
     * @param entityName The logical name of the entity specified in the entityId parameter.
     * @param id The ID of the record of the record to delete.
     */
    Observable Delete(@NonNull String entityName, @NonNull UUID id);

    /**
     * Deletes a record.
     * @param entityName The logical name of the entity specified in the entityId parameter.
     * @param id The ID of the record of the record to delete.
     * @param callback The callback interface that will be call on complete (use if you don't want to use RXJava)
     */
    void Delete(@NonNull String entityName, @NonNull UUID id, @NonNull Callback<?> callback);

    /**
     * Executes a message in the form of a request, and returns a response.
     * @param request The response from the request. You must cast the return value of this method to the specific instance of the response that corresponds to the Request parameter.
     */
    Observable<OrganizationResponse> Execute(OrganizationRequest request);

    /**
     * Executes a message in the form of a request, and returns a response.
     * @param request The response from the request. You must cast the return value of this method to the specific instance of the response that corresponds to the Request parameter.
     * @param callback The callback interface that will be call on complete (use if you don't want to use RXJava)
     */
    void Execute(@NonNull OrganizationRequest request, @NonNull Callback<OrganizationResponse> callback);

    /**
     * Retrieves a record.
     * @param entitySchemaName property_schemaname that is specified in the entityId parameter.
     * @param id property_entityid that you want to retrieve.
     * @param columnSet A query that specifies the set of columns, or attributes, to retrieve.
     */
    Observable<Entity> Retrieve(@NonNull String entitySchemaName, @NonNull UUID id, @NonNull ColumnSet columnSet);

    /**
     * Retrieves a record.
     * @param entitySchemaName property_schemaname that is specified in the entityId parameter.
     * @param id property_entityid that you want to retrieve.
     * @param columnSet A query that specifies the set of columns, or attributes, to retrieve.
     * @param callback The callback interface that will be call on complete (use if you don't want to use RXJava)
     */
    void Retrieve(@NonNull String entitySchemaName, @NonNull UUID id, @NonNull ColumnSet columnSet, @NonNull Callback<Entity> callback);

    /**
     *
     * @param entityName The logical name of the entity that is specified in the entityId parameter.
     * @param entityId property_entityid to which the related records are associated.
     * @param relationship The name of the relationship to be used to create the link.
     * @param relatedEntities property_relatedentities to be associated.
     */
    Observable Associate(String entityName, UUID entityId, Relationship relationship, EntityReferenceCollection relatedEntities);

    /**
     * Deletes a link between records.
     * @param entityName The logical name of the entity that is specified in the entityId parameter.
     * @param entityId The ID of the record from which the related records are disassociated.
     * @param relationship  The name of the relationship to be used to remove the link.
     * @param relatedEntities A collection of entity references (references to records) to be disassociated.
     */
    Observable Disassociate(String entityName, UUID entityId, Relationship relationship, EntityReferenceCollection relatedEntities);

    /**
     * Retrieves a collection of records.
     * @param query A query that determines the set of records to retrieve.
     */
    Observable<EntityCollection> RetrieveMultiple(@NonNull QueryBase query);

    /**
     * Retrieves a collection of records.
     * @param query A query that determines the set of records to retrieve.
     * @param callback The callback interface that will be call on complete (use if you don't want to use RXJava)
     */
    void RetrieveMultiple(@NonNull QueryBase query, @NonNull Callback<EntityCollection> callback);

    /**
     * Updates an existing record.
     * @param entity An entity instance that has one or more properties set to be updated in the record.
     */
    Observable Update(@NonNull Entity entity);

    /**
     * Updates an existing record.
     * @param entity An entity instance that has one or more properties set to be updated in the record.
     * @param callback The callback interface that will be call on complete (use if you don't want to use RXJava)
     */
    void Update(@NonNull Entity entity, @NonNull Callback<?> callback);
}
