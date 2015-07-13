package com.microsoft.xrm.sdk.Client;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Xml;

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

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.TypedString;

/**
 * Created on 3/5/2015.
 */
public class OrganizationServiceProxy extends ServiceProxy implements OrganizationService {

    private UUID CallerId;
    private Endpoint SoapEndpoint;

    interface Endpoint {

        @Headers({"Content-Type: text/xml; charset=utf-8"})
        @POST("/XRMServices/2011/Organization.svc/web/")
        void Soap(@Header("SOAPAction") String soapAction, @Body TypedString body, Callback<?> callback);

    }

    /**
     *
     * @param uri endpoint for all network calls
     * @param sessionToken oAuth Token
     */
    public OrganizationServiceProxy(String uri, String sessionToken) {
        super(uri, sessionToken);
        this.SoapEndpoint = buildSoapEndpoint();
    }

    /**
     *
     * @param uri endpoint for all network calls
     * @param authHeader the authentication header containing the oAuth token
     */
    public OrganizationServiceProxy(String uri, RequestInterceptor authHeader) {
        super(uri, authHeader);
        this.SoapEndpoint = buildSoapEndpoint();
    }

    public OrganizationServiceProxy(RestOrganizationServiceProxy oDataService) {
        super(oDataService.getEndpoint(), oDataService.getAuthHeader());
        this.SoapEndpoint = buildSoapEndpoint();
    }

    private Endpoint buildSoapEndpoint() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getEndpoint())
                .setConverter(new StringConverter())
                .setRequestInterceptor(getAuthHeader())
                .build();

        return restAdapter.create(Endpoint.class);
    }

    @Override
    public void Create(Entity entity, final Callback<UUID> callback) {
        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Create>");
        content.append(Utils.objectToXml(entity, "d:entity", true));
        content.append("</d:Create>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.CREATE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
                UUID newId = new UUID(0L, 0L);

                try {

                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                    parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
                    do {
                        parser.next();
                    } while(!parser.getName().equals("CreateResult"));
                    parser.require(XmlPullParser.START_TAG, "http://schemas.microsoft.com/xrm/2011/Contracts/Services", "CreateResult");
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        newId = UUID.fromString(parser.getText());
                    }
                }
                catch(Exception ex) {
                    ex.getCause().printStackTrace();
                }

                callback.success(newId, response);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    /**
     * Soap Delete Request
     * @param logicalName The logical name of the entity specified in the entityId parameter.
     * @param id The ID of the record of the record to delete.
     */
    @Override
    public void Delete(String logicalName, UUID id, @Nullable final Callback<?> callback) {
        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Delete>");
        content.append("<d:entityName>" + Utils.encodeXML(logicalName) + "</d:entityName>");
        content.append("<d:id>" + Utils.encodeXML(id.toString()) + "</d:id>");
        content.append("</d:Delete>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.DELETE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
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

    @Override
    public void Execute(final OrganizationRequest request, final Callback<OrganizationResponse> callback) {
        final StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Execute>");
        content.append(request.getRequestBody());
        content.append("</d:Execute>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.EXECUTE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
                OrganizationResponse orgResponse = request.getResponseType();

                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                    parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
                    do {
                        parser.next();
                    } while(parser.getEventType() != XmlPullParser.START_TAG || !parser.getName().equals("Results"));
                    parser.require(XmlPullParser.START_TAG, "http://schemas.microsoft.com/xrm/2011/Contracts", "Results");

                    orgResponse.storeResult(parser);
                }
                catch(Exception ex) {
                    ex.getCause().printStackTrace();
                }

                callback.success(orgResponse, response);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    /**
     *
     * @param logicalName Entity logical name
     * @param id property_entityid that you want to retrieve.
     * @param columnSet A query that specifies the set of columns, or attributes, to retrieve.
     * @param callback callback response
     */
    @Override
    public void Retrieve(String logicalName, UUID id, @NonNull ColumnSet columnSet, final Callback<Entity> callback) {
        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Retrieve>");
        content.append("<d:entityName>" + Utils.encodeXML(logicalName) + "</d:entityName>");
        content.append("<d:id>" + Utils.encodeXML(id.toString()) + "</d:id>");
        content.append(Utils.objectToXml(columnSet, "d:columnSet", true));
        content.append("</d:Retrieve>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.RETRIEVE, new TypedString(content.toString()), new Callback<String>() {

            @Override
            public void success(String xml, Response response) {
                Entity entity = null;

                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                    parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
                    do {
                        parser.next();
                    } while(!parser.getName().equals("RetrieveResult"));
                    parser.require(XmlPullParser.START_TAG, "http://schemas.microsoft.com/xrm/2011/Contracts/Services", "RetrieveResult");

                    entity = Entity.loadFromXml(parser);
                }
                catch(Exception ex) {
                    ex.getCause().printStackTrace();
                }

                if (entity != null) {
                    callback.success(entity, response);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    /**
     *
     * @param logicalName entity logical name
     * @param entityId property_entityid to which the related records are associated.
     * @param relationship The name of the relationship to be used to create the link.
     * @param relatedEntities property_relatedentities to be associated.
     */
    @Override
    public void Associate(String logicalName, UUID entityId, Relationship relationship,
                          EntityReferenceCollection relatedEntities, @Nullable final Callback<?> callback) {
        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Associate>");
        content.append("<d:entityName>" + Utils.encodeXML(logicalName) + "</d:entityName>");
        content.append("<d:entityId>" + Utils.encodeXML(entityId.toString()) + "</d:entityId>");
        content.append(Utils.objectToXml(relationship, "d:relationship", true));
        content.append(Utils.objectToXml(relatedEntities, "d:relatedEntities", true));
        content.append("</d:Associate>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.ASSOCIATE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
                //do nothing
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

    /**
     *
     * @param logicalName entity logical name
     * @param entityId The ID of the record from which the related records are disassociated.
     * @param relationship  The name of the relationship to be used to remove the link.
     * @param relatedEntities A collection of entity references (references to records) to be disassociated.
     */
    @Override
    public void Disassociate(String logicalName, UUID entityId, Relationship relationship,
                             EntityReferenceCollection relatedEntities, @Nullable final Callback<?> callback) {
        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Disassociate>");
        content.append("<d:entityName>" + Utils.encodeXML(logicalName) + "</d:entityName>");
        content.append("<d:entityId>" + Utils.encodeXML(entityId.toString()) + "</d:entityId>");
        content.append(Utils.objectToXml(relationship, "d:relationship", true));
        content.append(Utils.objectToXml(relatedEntities, "d:relatedEntities", true));
        content.append("</d:Disassociate>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.DISASSOCIATE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
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

    /**
     * Soap RetrieveMultiple Request using Fetch Expression
     * @param query A query that determines the set of records to retrieve.
     * @param callback
     */
    @Override
    public void RetrieveMultiple(QueryBase query, final Callback<EntityCollection> callback) {

        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:RetrieveMultiple>");
        content.append(Utils.objectToXml(query, "d:query", null));
        content.append("</d:RetrieveMultiple>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.RETRIEVE_MULTIPLE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
                EntityCollection entityCollection = null;

                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                    parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
                    do {
                        parser.next();
                    } while(!parser.getName().equals("RetrieveMultipleResult"));
                    parser.require(XmlPullParser.START_TAG, "http://schemas.microsoft.com/xrm/2011/Contracts/Services", "RetrieveMultipleResult");

                    entityCollection = EntityCollection.loadFromXml(parser);
                } catch (Exception ex) {
                    ex.getCause().printStackTrace();
                }

                if (entityCollection != null) {
                    callback.success(entityCollection, response);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    @Override
    public void Update(Entity entity, @Nullable final Callback<?> callback) {
        StringBuilder content = new StringBuilder();
        content.append(GetEnvelopeHeader());
        content.append("<s:Body>");
        content.append("<d:Update>");
        content.append(Utils.objectToXml(entity, "d:entity", true));
        content.append("</d:Update>");
        content.append("</s:Body>");
        content.append("</s:Envelope>");

        SoapEndpoint.Soap(SoapActions.UPDATE, new TypedString(content.toString()), new Callback<String>() {
            @Override
            public void success(String xml, Response response) {
                // no response
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

    private String GetEnvelopeHeader()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<s:Envelope xmlns:s='http://schemas.xmlsoap.org/soap/envelope/' xmlns:a='http://schemas.microsoft.com/xrm/2011/Contracts' xmlns:i='http://www.w3.org/2001/XMLSchema-instance' xmlns:b='http://schemas.datacontract.org/2004/07/System.Collections.Generic' xmlns:c='http://www.w3.org/2001/XMLSchema' xmlns:d='http://schemas.microsoft.com/xrm/2011/Contracts/Services' xmlns:e='http://schemas.microsoft.com/2003/10/Serialization/' xmlns:f='http://schemas.microsoft.com/2003/10/Serialization/Arrays' xmlns:g='http://schemas.microsoft.com/crm/2011/Contracts' xmlns:h='http://schemas.microsoft.com/xrm/2011/Metadata' xmlns:j='http://schemas.microsoft.com/xrm/2011/Metadata/Query' xmlns:k='http://schemas.microsoft.com/xrm/2013/Metadata' xmlns:l='http://schemas.microsoft.com/xrm/2012/Contracts'>");
        stringBuilder.append("<s:Header>");
        if (this.CallerId != null && this.CallerId != new UUID(0L, 0L)) {
            stringBuilder.append("<a:CallerId>" + this.CallerId.toString() + "</a:CallerId>");
        }
        stringBuilder.append("<a:SdkClientVersion>6.0</a:SdkClientVersion>");
        stringBuilder.append("</s:Header>");
        return stringBuilder.toString();
    }
}
