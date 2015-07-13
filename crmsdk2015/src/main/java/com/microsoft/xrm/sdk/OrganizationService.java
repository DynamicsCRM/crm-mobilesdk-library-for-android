package com.microsoft.xrm.sdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Query.QueryBase;

import java.util.UUID;

import retrofit.Callback;

/**
 * Created on 3/5/2015.
 */
public interface OrganizationService {
    /**
     * Creates a record.
     * @param entity An entity instance that contains the properties to set in the newly created record.
     */
    void Create(Entity entity, Callback<UUID> callback);

    /**
     * Deletes a record.
     * @param entityName The logical name of the entity specified in the entityId parameter.
     * @param id The ID of the record of the record to delete.
     * @param callback Optional callback for if you want to handle the failure
     */
    void Delete(String entityName, UUID id, @Nullable Callback<?> callback);

    /**
     * Executes a message in the form of a request, and returns a response.
     * @param request The response from the request. You must cast the return value of this method to the specific instance of the response that corresponds to the Request parameter.
     */
    void Execute(OrganizationRequest request, Callback<OrganizationResponse> callback);

    /**
     * Retrieves a record.
     * @param entitySchemaName property_schemaname that is specified in the entityId parameter.
     * @param id property_entityid that you want to retrieve.
     * @param columnSet A query that specifies the set of columns, or attributes, to retrieve.
     */
    void Retrieve(String entitySchemaName, UUID id, @NonNull ColumnSet columnSet,
                  Callback<Entity> callback);

    /**
     *
     * @param entityName The logical name of the entity that is specified in the entityId parameter.
     * @param entityId property_entityid to which the related records are associated.
     * @param relationship The name of the relationship to be used to create the link.
     * @param relatedEntities property_relatedentities to be associated.
     * @param callback Optional callback for if you want to handle the failure
     */
    void Associate(String entityName, UUID entityId, Relationship relationship,
                   EntityReferenceCollection relatedEntities, @Nullable Callback<?> callback);

    /**
     * Deletes a link between records.
     * @param entityName The logical name of the entity that is specified in the entityId parameter.
     * @param entityId The ID of the record from which the related records are disassociated.
     * @param relationship  The name of the relationship to be used to remove the link.
     * @param relatedEntities A collection of entity references (references to records) to be disassociated.
     * @param callback Optional callback for if you want to handle the failure
     */
    void Disassociate(String entityName, UUID entityId, Relationship relationship, EntityReferenceCollection relatedEntities,
                      @Nullable Callback<?> callback);

    /**
     * Retrieves a collection of records.
     * @param query A query that determines the set of records to retrieve.
     */
    void RetrieveMultiple(QueryBase query, Callback<EntityCollection> callback);

    /**
     * Updates an existing record.
     * @param entity An entity instance that has one or more properties set to be updated in the record.
     */
    void Update(Entity entity, @Nullable Callback<?> callback);
}
