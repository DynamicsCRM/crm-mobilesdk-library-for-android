package com.microsoft.xrm.sdk.Client;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.EntityCollection;
import com.microsoft.xrm.sdk.RestOrganizationService;
import com.microsoft.xrm.sdk.Utils;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.mime.TypedString;

/**
 * Created on 3/30/2015.
 */
public class RestOrganizationServiceProxy extends ServiceProxy implements RestOrganizationService {

    private Endpoint RestEndpoint;

    interface Endpoint {
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json;odata=verbose"
        })
        @POST("/XRMServices/2011/OrganizationData.svc/{schemaName}Set")
        void oDataPost(@Path("schemaName") String schemaName, @Body TypedString body, Callback<?> callback);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json;odata=verbose",
                "X-HTTP-Method: MERGE"
        })
        @POST("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')")
        void oDataPost(@Path("schemaName") String schemaName, @Path("guid") UUID uid, @Body TypedString body,
                       Callback<?> callback);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json;odata=verbose"
        })
        @POST("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')/{relationship}")
        void oDataPost(@Path("schemaName") String schemaName, @Path("guid") UUID uid,
                       @Path("relationship") String relationshipName, @Body TypedString body,
                       Callback<?> callback);

        @Headers({"Accept: application/json"})
        @GET("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')/{relationship}")
        void oDataGet(@Path("schemaName") String schemaName, @Path("guid") UUID uid, @Path("relationship") String relationship,
                      @QueryMap Map<String, String> queries, Callback<?> callback);

        @Headers({"Accept: application/json"})
        @GET("/XRMServices/2011/OrganizationData.svc/{schemaName}Set")
        void oDataGet(@Path("schemaName") String schemaName, @QueryMap Map<String, String> queries, Callback<?> callback);

        @Headers({"Accept: application/json"})
        @DELETE("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')")
        void oDataDelete(@Path("schemaName") String schemaName, @Path("guid") UUID uid, Callback<?> callback);
    }

    /**
     *
     * @param uri endpoint for all network calls
     * @param sessionToken oAuth Token
     */
    public RestOrganizationServiceProxy(String uri, String sessionToken) {
        super(uri, sessionToken);
        RestEndpoint = this.buildRestEndpoint();
    }

    /**
     *
     * @param uri endpoint for all network calls
     * @param authHeader the authentication header containing the oAuth token
     */
    public RestOrganizationServiceProxy(String uri, RequestInterceptor authHeader) {
        super(uri, authHeader);
        RestEndpoint = this.buildRestEndpoint();
    }

    public RestOrganizationServiceProxy(OrganizationServiceProxy orgService) {
        super(orgService.getEndpoint(), orgService.getAuthHeader());
        RestEndpoint = this.buildRestEndpoint();
    }

    @Override
    public void Create(Entity entity,  final Callback<UUID> callback) throws InvalidClassException {
        if (entity.getClass().getSuperclass() != Entity.class) {
            throw new InvalidClassException("Class is not a subclass of entity, please use the Create(Entity, String, Callback) method");
        }

        Gson gson = new Gson();
        String body = gson.toJson(Utils.getSchemaAttributes(entity));

        RestEndpoint.oDataPost(entity.getClass().getSimpleName(), new TypedString(body),
                new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        Entity entity = Entity.loadFromJson((LinkedTreeMap) ((LinkedTreeMap) o).get("d"));
                        callback.success(entity.getId(), response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        callback.failure(error);
                    }
                });
    }

    @Override
    public void Create(Entity relatedTo, Entity entity, String relationshipName, final Callback<UUID> callback) throws InvalidClassException {
        if (relatedTo.getClass().getSuperclass() != Entity.class) {
            throw new InvalidClassException("Class is not a subclass of entity, please use the Create(Entity, String, Callback) method");
        }

        Gson gson = new Gson();
        String body;
        if (entity.getClass().getSuperclass() != Entity.class) {
            body = gson.toJson(entity.getAttributes());
        }
        else {
            body = gson.toJson(Utils.getSchemaAttributes(entity));
        }
        RestEndpoint.oDataPost(relatedTo.getClass().getSimpleName(), relatedTo.getId(), relationshipName,
                new TypedString(body), new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        Entity entity = Entity.loadFromJson((LinkedTreeMap) ((LinkedTreeMap) o).get("d"));
                        callback.success(entity.getId(), response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        callback.failure(error);
                    }
                });
    }

    /**
     * oData Delete Request
     * @param entitySchemaName The schema name of the entity specified in the entityId parameter.
     * @param id The ID of the record of the record to delete.
     */
    @Override
    public void Delete(String entitySchemaName, UUID id, @Nullable final Callback<?> callback) {
        RestEndpoint.oDataDelete(entitySchemaName, id, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                // do nothing
            }

            @Override
            public void failure(RetrofitError error) {
                if (callback != null) {
                    callback.failure(error);
                } else {
                    throw error;
                }
            }
        });
    }

//    /**
//     * Rest oData call for Retrieve using Query parameters
//     * @param entitySchemaName
//     * @param id
//     * @param queryOptions
//     * @param callback
//     */
//    @Override
//    public void Retrieve(String entitySchemaName, UUID id, @NonNull QueryOptions queryOptions,
//                         final Callback<Entity> callback) {
//
//        RestEndpoint.oDataGet(entitySchemaName, id, queryOptions.getQueryMap(),
//                new Callback<Object>() {
//                    @Override
//                    public void success(Object o, Response response) {
//                        Entity entity = new Entity();
////                        EntityCollection entityCollection = new EntityCollection();
////                        ArrayList<LinkedTreeMap> entities = (ArrayList<LinkedTreeMap>) find((LinkedTreeMap) o, "results");
////
////                        for (LinkedTreeMap treeMap : entities) {
////                            entityCollection.getEntities().add(Entity.loadFromJson(treeMap));
////                        }
////
////                        try {
////                            entityCollection.setTotalRecordCount(entities.size());
////                        } catch (Exception ex) {
////                        }
////
////                        callback.success(entityCollection, response);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        callback.failure(error);
//                    }
//                });
//    }

    /**
     * Rest oData call for Retrieve using Query parameters
     * @param entitySchemaName
     * @param id
     * @param relationshipName
     * @param queryOptions
     * @param callback
     */
    @Override
    public void RetrieveMultiple(String entitySchemaName, UUID id, String relationshipName,
                                 @NonNull QueryOptions queryOptions, final Callback<EntityCollection> callback) {

        RestEndpoint.oDataGet(entitySchemaName, id, relationshipName, queryOptions.getQueryMap(),
                new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        EntityCollection entityCollection = new EntityCollection();
                        ArrayList<LinkedTreeMap> entities = (ArrayList<LinkedTreeMap>) find((LinkedTreeMap) o, "results");

                        for (LinkedTreeMap treeMap : entities) {
                            entityCollection.getEntities().add(Entity.loadFromJson(treeMap));
                        }

                        try {
                            entityCollection.setTotalRecordCount(entities.size());
                        } catch (Exception ex) {
                        }

                        callback.success(entityCollection, response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        callback.failure(error);
                    }
                });
    }


    /**
     * oData RetrieveMultiple Request using Query Parameters
     * @param entitySchemaName entity Schema Name (usually includes capital letters and ISN'T the logical name)
     * @param query
     * @param callback
     */
    @Override
    public void RetrieveMultiple(String entitySchemaName, QueryOptions query, final Callback<EntityCollection> callback) {
        RestEndpoint.oDataGet(entitySchemaName, query.getQueryMap(), new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                EntityCollection entityCollection = new EntityCollection();
                ArrayList<LinkedTreeMap> entities = (ArrayList<LinkedTreeMap>) find((LinkedTreeMap) o, "results");

                for (LinkedTreeMap treeMap : entities) {
                    entityCollection.getEntities().add(Entity.loadFromJson(treeMap));
                }

                try {
                    entityCollection.setTotalRecordCount(entities.size());
                } catch (Exception ex) {
                }

                callback.success(entityCollection, response);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void Update(Entity entity, @Nullable final Callback<?> callback) throws InvalidClassException {
        if (entity.getClass().getSuperclass() != Entity.class) {
            throw new InvalidClassException("Class is not a subclass of entity, please use the Create(Entity, String, Callback) method");
        }

        Gson gson = new Gson();
        RestEndpoint.oDataPost(entity.getClass().getSimpleName(), entity.getId(), new TypedString(gson.toJson(entity)),
                new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        // do nothing
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (callback != null) {
                            callback.failure(error);
                        } else {
                            throw error;
                        }
                    }
                });
    }

    private Object find(LinkedTreeMap<String, Object> source, String findKey) {
        if (source.keySet().contains(findKey)) {
            return source.get(findKey);
        }
        else {
            for (String key : source.keySet()) {
                if (source.get(key) instanceof LinkedTreeMap<?,?>) {
                    return find((LinkedTreeMap<String, Object>)source.get(key), findKey);
                }
            }
        }

        return null;
    }

    private Endpoint buildRestEndpoint() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getEndpoint())
                .setRequestInterceptor(getAuthHeader())
                .build();

        return restAdapter.create(Endpoint.class);
    }
}
