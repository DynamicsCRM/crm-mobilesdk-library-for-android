package com.microsoft.xrm.sdk;

import android.support.annotation.NonNull;

import com.microsoft.xrm.sdk.Client.QueryOptions;

import java.util.UUID;

import rx.Observable;

public interface RestOrganizationService {

    /**
     * Creates a record.
     * @param entity An entity instance that contains the properties to set in the newly created record.
     *               must be a subclass of entity to use this method.
     */
    Observable Create(Entity entity);

    void Create(Entity entity, Callback<UUID> callback);

    Observable Create(Entity relatedTo, Entity create, String relationshipName);

    void Create(Entity relatedTo, Entity create, String relationshipName, Callback<UUID> callback);

    Observable Create(String relatedToSchemaName, UUID relatedToId, Entity create, String relationshipName);

    void Create(String relatedToSchemaName, UUID relatedToId, Entity create, String relationshipName,
                          Callback<UUID> callback);

    Observable Delete(String entitySchemaName, UUID id);

    void Delete(String entitySchemaName, UUID id, Callback<?> callback);

//    Observable<Entity> Retrieve(String entitySchemaName, UUID id, @NonNull QueryOptions queryOptions);
//
//    void Retrieve(String entitySchemaName, UUID id, @NonNull QueryOptions queryOptions, Callback<Entity> callback);

    Observable<EntityCollection> RetrieveMultiple(String entitySchemaName, UUID id, String relationshipName, @NonNull QueryOptions queryOptions);

    void RetrieveMultiple(String entitySchemaName, UUID id, String relationshipName, @NonNull QueryOptions queryOptions,
                                                Callback<EntityCollection> callback);

    Observable<EntityCollection> RetrieveMultiple(String entitySchemaName, QueryOptions query);

    void RetrieveMultiple(String entitySchemaName, QueryOptions query, Callback<EntityCollection> callback);

    /**
     * Updates an existing record.
     * @param entity An entity instance that has one or more properties set to be updated in the record.
     *               must be a subclass of entity to use this method.
     */
    Observable Update(Entity entity);

    void Update(Entity entity, Callback<?> callback);
}
