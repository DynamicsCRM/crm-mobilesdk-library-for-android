package com.microsoft.xrm.sdk.Client;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Xml;

import com.microsoft.xrm.sdk.Callback;
import com.microsoft.xrm.sdk.Client.Converters.StringConverterFactory;
import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.EntityCollection;
import com.microsoft.xrm.sdk.EntityReferenceCollection;
import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.OrganizationResponse;
import com.microsoft.xrm.sdk.ColumnSet;
import com.microsoft.xrm.sdk.OrganizationService;
import com.microsoft.xrm.sdk.Query.QueryBase;
import com.microsoft.xrm.sdk.Relationship;
import com.microsoft.xrm.sdk.Utils;
import com.microsoft.xrm.sdk.V5;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrganizationServiceProxy extends ServiceProxy implements OrganizationService {

    private UUID CallerId;
    private final XrmService xrmService;

    interface XrmService {
        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/RetrieveMultiple"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> retrieveMultiple(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Execute"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> execute(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Retrieve"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> retrieve(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Create"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> create(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Delete"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> delete(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Update"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> update(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Associate"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> associate(@Body String body);

        @Headers({"SOAPAction: http://schemas.microsoft.com/xrm/2011/Contracts/Services/IOrganizationService/Disassociate"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        Call<String> disassociate(@Body String body);
    }

    /**
     *
     * @param uri baseUri for all network calls
     * @param sessionToken oAuth Session Token
     */
    public OrganizationServiceProxy(@NonNull String uri, @NonNull String sessionToken) {
        super(uri, sessionToken);

        ArrayMap<String, String> headers = new ArrayMap<>();
        headers.put("Content-Type", "text/xml; charset=utf-8");
        this.addGlobalHeaders(headers);

        this.xrmService = this.buildService(new StringConverterFactory(), XrmService.class);
    }

    @Override
    public void Create(@NonNull Entity entity, @NonNull Callback<UUID> callback) {
        Create(entity)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(callback::failure)
            .doOnNext(callback::success)
            .subscribe();
    }

    @Override
    public Observable<UUID> Create(@NonNull Entity entity) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Create>")
                        .append(Utils.objectToXml(entity, "d:entity", true))
                    .append("</d:Create>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(createEntity(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    @Nullable
    private UUID createEntity(String content) throws Exception {
        Response<String> response = xrmService.create(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        parser.setInput(new ByteArrayInputStream(response.body().getBytes()), null);
        do {
            parser.next();
        } while (!parser.getName().equals("CreateResult"));

        parser.require(XmlPullParser.START_TAG, V5.Services, "CreateResult");
        parser.next();
        if (parser.getEventType() == XmlPullParser.TEXT) {
            return UUID.fromString(parser.getText());
        }
        else {
            return null;
        }
    }

    @Override
    public void Delete(@NonNull String entityName, @NonNull UUID id, @NonNull Callback<?> callback) {
        Delete(entityName, id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(callback::failure)
            .doOnNext(response -> {})
            .subscribe();
    }

    @Override
    public Observable<?> Delete(@NonNull String logicalName, @NonNull UUID id) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Delete>")
                        .append("<d:entityName>")
                            .append(Utils.encodeXML(logicalName))
                        .append("</d:entityName>")
                        .append("<d:id>")
                            .append(Utils.encodeXML(id.toString()))
                        .append("</d:id>")
                    .append("</d:Delete>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(delete(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    private String delete(String content) throws Exception {
        Response<String> response = xrmService.delete(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        return response.body();
    }

    @Override
    public void Execute(@NonNull OrganizationRequest request, @NonNull Callback<OrganizationResponse> callback) {
        Execute(request)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(callback::failure)
            .doOnNext(callback::success)
            .subscribe();
    }

    @Override
    public Observable<OrganizationResponse> Execute(@NonNull final OrganizationRequest request) {
        final StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Execute>")
                        .append(request.getRequestBody())
                    .append("</d:Execute>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
                try {
                    return Observable.just(execute(content.toString(), request));
                }
                catch(Exception ex) {
                    return Observable.error(ex);
                }
            });
    }

    private OrganizationResponse execute(String content, OrganizationRequest request) throws Exception {
        OrganizationResponse orgResponse = request.getResponseType();

        Response<String> response = xrmService.execute(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        parser.setInput(new ByteArrayInputStream(response.body().getBytes()), null);
        do {
            parser.next();
        }
        while (parser.getEventType() != XmlPullParser.START_TAG || !parser.getName().equals("Results"));
        parser.require(XmlPullParser.START_TAG, V5.Services, "Results");

        orgResponse.storeResult(parser);
        return orgResponse;
    }

    @Override
    public void Retrieve(@NonNull String entitySchemaName, @NonNull UUID id, @NonNull ColumnSet columnSet, @NonNull Callback<Entity> callback) {
        Retrieve(entitySchemaName, id, columnSet)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(callback::failure)
            .doOnNext(callback::success)
            .subscribe();
    }

    @Override
    public Observable<Entity> Retrieve(@NonNull String logicalName, @NonNull UUID id, @NonNull ColumnSet columnSet) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Retrieve>")
                        .append("<d:entityName>")
                            .append(Utils.encodeXML(logicalName))
                        .append("</d:entityName>")
                        .append("<d:id>")
                            .append(Utils.encodeXML(id.toString()))
                        .append("</d:id>")
                        .append(Utils.objectToXml(columnSet, "d:columnSet", true))
                    .append("</d:Retrieve>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(retrieveEntity(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    private Entity retrieveEntity(String content) throws Exception {
        Response<String> response = xrmService.retrieve(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        parser.setInput(new ByteArrayInputStream(response.body().getBytes()), null);
        do {
            parser.next();
        } while (!parser.getName().equals("RetrieveResult"));
        parser.require(XmlPullParser.START_TAG, V5.Services, "RetrieveResult");

        return Entity.loadFromXml(parser);
    }

    @Override
    public Observable Associate(@NonNull String logicalName, @NonNull UUID entityId, @NonNull Relationship relationship,
                          @NonNull EntityReferenceCollection relatedEntities) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Associate>")
                        .append("<d:entityName>")
                            .append(Utils.encodeXML(logicalName))
                        .append("</d:entityName>")
                        .append("<d:entityId>")
                            .append(Utils.encodeXML(entityId.toString()))
                        .append("</d:entityId>")
                        .append(Utils.objectToXml(relationship, "d:relationship", true))
                        .append(Utils.objectToXml(relatedEntities, "d:relatedEntities", true))
                    .append("</d:Associate>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(associate(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    private String associate(String content) throws Exception {
        Response<String> response = xrmService.associate(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        return response.body();
    }

    @Override
    public Observable Disassociate(@NonNull String logicalName, @NonNull UUID entityId, @NonNull Relationship relationship,
                             @NonNull EntityReferenceCollection relatedEntities) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Disassociate>")
                        .append("<d:entityName>")
                            .append(Utils.encodeXML(logicalName))
                        .append("</d:entityName>")
                        .append("<d:entityId>")
                            .append(Utils.encodeXML(entityId.toString()))
                        .append("</d:entityId>")
                        .append(Utils.objectToXml(relationship, "d:relationship", true))
                        .append(Utils.objectToXml(relatedEntities, "d:relatedEntities", true))
                    .append("</d:Disassociate>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(disassociate(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    private String disassociate(String content) throws Exception {
        Response<String> response = xrmService.disassociate(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        return response.body();
    }

    @Override
    public void RetrieveMultiple(@NonNull QueryBase query, @NonNull Callback<EntityCollection> callback) {
        RetrieveMultiple(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(callback::failure)
            .doOnNext(callback::success)
            .subscribe();
    }

    @Override
    public Observable<EntityCollection> RetrieveMultiple(@NonNull QueryBase query) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:RetrieveMultiple>")
                        .append(Utils.objectToXml(query, "d:query", null))
                    .append("</d:RetrieveMultiple>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(retrieveMultiple(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    @Nullable
    private EntityCollection retrieveMultiple(String content) throws Exception {
        String response = xrmService.retrieveMultiple(content).execute().body();
        if (response == null || response.equals("")) {
            return null;
        }

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        parser.setInput(new ByteArrayInputStream(content.getBytes()), null);
        do {
            parser.next();
        } while (parser.getName() == null || !parser.getName().equals("RetrieveMultipleResult"));
        parser.require(XmlPullParser.START_TAG, V5.Services, "RetrieveMultipleResult");

        return EntityCollection.loadFromXml(parser);
    }

    @Override
    public void Update(@NonNull Entity entity, @NonNull Callback<?> callback) {
        Update(entity)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(callback::failure)
            .doOnNext(response -> {})
            .subscribe();
    }

    @Override
    public Observable<?> Update(@NonNull Entity entity) {
        StringBuilder content = new StringBuilder()
            .append(GetEnvelopeHeader())
                .append("<s:Body>")
                    .append("<d:Update>")
                        .append(Utils.objectToXml(entity, "d:entity", true))
                    .append("</d:Update>")
                .append("</s:Body>")
            .append("</s:Envelope>");

        return Observable.defer(() -> {
            try {
                return Observable.just(update(content.toString()));
            }
            catch(Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    private String update(String content) throws Exception {
        Response<String> response = xrmService.update(content).execute();
        if (!response.isSuccessful() || response.body() == null || response.body().equals("")) {
            throw new Exception(response.errorBody().string());
        }

        return response.body();
    }

    @NonNull
    private String GetEnvelopeHeader() {
        StringBuilder stringBuilder = new StringBuilder()
            .append("<s:Envelope xmlns:s='http://schemas.xmlsoap.org/soap/envelope/' xmlns:a='http://schemas.microsoft.com/xrm/2011/Contracts' xmlns:i='http://www.w3.org/2001/XMLSchema-instance' xmlns:b='http://schemas.datacontract.org/2004/07/System.Collections.Generic' xmlns:c='http://www.w3.org/2001/XMLSchema' xmlns:d='http://schemas.microsoft.com/xrm/2011/Contracts/Services' xmlns:e='http://schemas.microsoft.com/2003/10/Serialization/' xmlns:f='http://schemas.microsoft.com/2003/10/Serialization/Arrays' xmlns:g='http://schemas.microsoft.com/crm/2011/Contracts' xmlns:h='http://schemas.microsoft.com/xrm/2011/Metadata' xmlns:j='http://schemas.microsoft.com/xrm/2011/Metadata/Query' xmlns:k='http://schemas.microsoft.com/xrm/2013/Metadata' xmlns:l='http://schemas.microsoft.com/xrm/2012/Contracts'>")
                .append("<s:Header>");
        if (this.CallerId != null && this.CallerId != new UUID(0L, 0L)) {
            stringBuilder
                .append("<a:CallerId>")
                    .append(this.CallerId.toString())
                .append("</a:CallerId>");
        }
        stringBuilder
                .append("<a:SdkClientVersion>6.0</a:SdkClientVersion>")
            .append("</s:Header>");
        return stringBuilder.toString();
    }
}
