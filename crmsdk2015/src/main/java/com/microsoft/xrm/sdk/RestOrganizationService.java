package com.microsoft.xrm.sdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Client.QueryOptions;

import java.io.InvalidClassException;
import java.util.UUID;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created on 3/30/2015.
 */
public interface RestOrganizationService {

    /**
     * Creates a record.
     * @param entity An entity instance that contains the properties to set in the newly created record.
     *               must be a subclass of entity to use this method.
     */
    void Create(Entity entity, Callback<UUID> callback) throws InvalidClassException;

    void Create(Entity relatedTo, Entity create, String relationshipName, Callback<UUID> callback) throws InvalidClassException;

    /**
     * Deletes a record.
     * @param entitySchemaName The logical name of the entity specified in the entityId parameter.
     * @param id The ID of the record of the record to delete.
     */
    void Delete(String entitySchemaName, UUID id, @Nullable Callback<?> callback);

    /**
     *
     * @param entitySchemaName
     * @param id
     * @param queryOptions
     * @param callback
     */
//    void Retrieve(String entitySchemaName, UUID id, @NonNull QueryOptions queryOptions,
//                  Callback<Entity> callback);

    /**
     *
     * @param entitySchemaName
     * @param id
     * @param relationshipName
     * @param queryOptions
     * @param callback
     */
    void RetrieveMultiple(String entitySchemaName, UUID id, String relationshipName,
                                 @NonNull QueryOptions queryOptions, Callback<EntityCollection> callback);

    /**
     *
     * @param entitySchemaName
     * @param query
     * @param callback
     */
    void RetrieveMultiple(String entitySchemaName, QueryOptions query, Callback<EntityCollection> callback);

    /**
     * Updates an existing record.
     * @param entity An entity instance that has one or more properties set to be updated in the record.
     *               must be a subclass of entity to use this method.
     */
    void Update(Entity entity, @Nullable Callback<?> callback) throws InvalidClassException;
}
