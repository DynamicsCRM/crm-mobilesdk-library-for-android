package com.microsoft.xrm.sdk.Client;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.EntityCollection;
import com.microsoft.xrm.sdk.RestOrganizationService;
import com.microsoft.xrm.sdk.Utils;

import java.io.InvalidClassException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public class RestOrganizationServiceProxy extends ServiceProxy implements RestOrganizationService {

    private Gson gson;
    private oDataService odataService;

    interface oDataService {

        @Headers({ "Content-Type: application/json;odata=verbose" })
        @POST("/XRMServices/2011/OrganizationData.svc/{schemaName}Set")
        Call oDataPost(@Path("schemaName") String schemaName, @Body String body);

        @Headers({
                "Content-Type: application/json;odata=verbose",
                "X-HTTP-Method: MERGE"
        })
        @POST("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')")
        Call oDataPost(@Path("schemaName") String schemaName, @Path("guid") UUID uid, @Body String body);

        @Headers({ "Content-Type: application/json;odata=verbose" })
        @POST("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')/{relationship}")
        Call oDataPost(@Path("schemaName") String schemaName, @Path("guid") UUID uid,
                       @Path("relationship") String relationshipName, @Body String body);

        @GET("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')/{relationship}")
        Call oDataGet(@Path("schemaName") String schemaName, @Path("guid") UUID uid, @Path("relationship") String relationship,
                      @QueryMap Map<String, String> queries);

        @GET("/XRMServices/2011/OrganizationData.svc/{schemaName}Set")
        Call oDataGet(@Path("schemaName") String schemaName, @QueryMap Map<String, String> queries);

        @DELETE("/XRMServices/2011/OrganizationData.svc/{schemaName}Set(guid'{guid}')")
        Call oDataDelete(@Path("schemaName") String schemaName, @Path("guid") UUID uid);

    }

    public RestOrganizationServiceProxy(@NonNull String uri, @NonNull String sessionToken) {
        super(uri, sessionToken);

        ArrayMap<String, String> headers = new ArrayMap<>();
        headers.put("Accept", "application/json");
        this.addGlobalHeaders(headers);
        this.gson = new Gson();

        this.odataService = this.buildService(GsonConverterFactory.create(), oDataService.class);
    }

    private void validateEntitySuperclass(Entity passed) throws InvalidClassException {
        if (passed.getClass().getSuperclass() != Entity.class) {
            throw new InvalidClassException("Class is not a subclass of entity, please use the Create(Entity, String, Callback) method");
        }
    }

    private UUID create(Response response) throws Exception {
        if (!response.isSuccessful() || response.body() == null) {
            throw new Exception(response.errorBody().string());
        }

        Entity entity = Entity.loadFromJson((LinkedTreeMap) ((LinkedTreeMap) response.body()).get("d"));
        return entity.getId();
    }

    @Override
    public Observable Create(@NonNull Entity entity) {
        try {
            validateEntitySuperclass(entity);
            String body = gson.toJson(Utils.getSchemaAttributes(entity));

            Response response = odataService.oDataPost(entity.getClass().getSimpleName(), body).execute();
            return Observable.just(create(response));
        }
        catch(Exception ex) {
            return Observable.error(ex);
        }
    }

    @Override
    public Observable<UUID> Create(@NonNull Entity relatedTo, @NonNull Entity entity, @NonNull String relationshipName) {
        try {
            validateEntitySuperclass(relatedTo);

            String body;
            if (entity.getClass().getSuperclass() != Entity.class) {
                body = gson.toJson(entity.getAttributes());
            }
            else {
                body = gson.toJson(Utils.getSchemaAttributes(entity));
            }

            Response response = odataService
                    .oDataPost(relatedTo.getClass().getSimpleName(), relatedTo.getId(), relationshipName, body)
                    .execute();
            return Observable.just(create(response));
        }
        catch(Exception ex) {
            return Observable.error(ex);
        }
    }

    @Override
    public Observable<UUID> Create(@NonNull String relatedToSchemaName, @NonNull UUID relatedToId, @NonNull Entity create, @NonNull String relationshipName) {
        try {
            String body;
            if (create.getClass().getSuperclass() != Entity.class) {
                body = gson.toJson(create.getAttributes());
            }
            else {
                body = gson.toJson(Utils.getSchemaAttributes(create));
            }

            Response response = odataService
                    .oDataPost(relatedToSchemaName, relatedToId, relationshipName, body)
                    .execute();
            return Observable.just(create(response));
        }
        catch(Exception ex) {
            return Observable.error(ex);
        }
    }

    @Override
    public Observable Delete(@NonNull String entitySchemaName, @NonNull UUID id) {
        try {
            Response response  = odataService.oDataDelete(entitySchemaName, id).execute();
            if (!response.isSuccessful() || response.body() == null) {
                throw new Exception(response.errorBody().string());
            }

            return Observable.just(response.body());
        }
        catch(Exception ex) {
            return Observable.error(ex);
        }
    }

    private EntityCollection retrieveMultiple(Response response) throws Exception {
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        EntityCollection entityCollection = new EntityCollection();
        List<LinkedTreeMap> entities = (List<LinkedTreeMap>) find((LinkedTreeMap) response.body(), "results");

        for (LinkedTreeMap treeMap : entities) {
            entityCollection.getEntities().add(Entity.loadFromJson(treeMap));
        }

        entityCollection.setTotalRecordCount(entities.size());
        return entityCollection;
    }

    @Override
    public Observable<EntityCollection> RetrieveMultiple(@NonNull String entitySchemaName, @NonNull UUID id, @NonNull String relationshipName, @NonNull QueryOptions queryOptions) {
        return Observable.defer(() -> {
            try {
                Response response = odataService
                    .oDataGet(entitySchemaName, id, relationshipName, queryOptions.getQueryMap())
                    .execute();
                return Observable.just(retrieveMultiple(response));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    @Override
    public Observable<EntityCollection> RetrieveMultiple(@NonNull String entitySchemaName, @NonNull QueryOptions query) {
        return Observable.defer(() -> {
            try {
                Response response = odataService.oDataGet(entitySchemaName, query.getQueryMap()).execute();
                return Observable.just(retrieveMultiple(response));
            }
            catch (Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    @Override
    public Observable Update(@NonNull Entity entity) {
        try {
            validateEntitySuperclass(entity);

            Response response = odataService
                    .oDataPost(entity.getClass().getSimpleName(), entity.getId(), gson.toJson(entity))
                    .execute();
            if (!response.isSuccessful() || response.body() == null) {
                throw new Exception(response.errorBody().string());
            }

            return Observable.just(response.body());
        }
        catch(Exception ex) {
            return Observable.error(ex);
        }
    }

    @Nullable
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

}
