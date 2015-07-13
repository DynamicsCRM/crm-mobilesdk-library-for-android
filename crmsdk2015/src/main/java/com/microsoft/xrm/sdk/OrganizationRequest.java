package com.microsoft.xrm.sdk;

import java.util.UUID;

/**
 * Created on 3/5/2015.
 */
public class OrganizationRequest {

    private DataMapCollection<String, Object> Parameters;
    private UUID RequestId;
    private String RequestName;
    private OrganizationResponse ResponseType;

    public OrganizationRequest() {
        Parameters = new DataMapCollection<>();
    }

    public OrganizationRequest(String requestName) {
        this();
        RequestName = requestName;
    }

    public OrganizationResponse getResponseType() {
        return ResponseType;
    }

    protected void setResponseType(OrganizationResponse responseType) {
        ResponseType = responseType;
    }

    public String getRequestBody() {
        return "";
    }

    public String getSoapBody()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<d:request>");
        sb.append((this.Parameters.size() == 0) ? "<a:Parameters />" :
                "<a:Parameters>" + GetParameters() + "</a:Parameters>");
        sb.append((this.RequestId == null || this.RequestId == new UUID(0L, 0L)) ?
                "<a:RequestId i:nil='true' />":
        "<a:RequestId>" + RequestId.toString() + "</a:RequestId>");
        sb.append("<a:RequestName>" + Utils.encodeXML(RequestName) + "</a:RequestName>");
        sb.append("</d:request>");
        return sb.toString();
    }

    String GetParameters()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : Parameters.keySet())
        {
            Object parameter = Parameters.get(key);

            if (parameter == null)
                continue;
            stringBuilder.append("<a:KeyValuePairOfstringanyType>");
            stringBuilder.append("<b:key>" + Utils.encodeXML(key) + "</b:key>");
            stringBuilder.append(Utils.objectToXml(parameter, "b:value", null));
            stringBuilder.append("</a:KeyValuePairOfstringanyType>");
        }
        return stringBuilder.toString();
    }

    public String getRequestName() {
        return RequestName;
    }

    public void setRequestName(String requestName) {
        this.RequestName = requestName;
    }

    public UUID getRequestId() {
        return RequestId;
    }

    public void setRequestId(UUID requestId) {
        this.RequestId = requestId;
    }

    public DataMapCollection<String, Object> getParameters() {
        if (this.Parameters == null) {
            this.Parameters = new DataMapCollection<String, Object>();
        }

        return this.Parameters;
    }

    public void set(String parameterName, Object value) {
        this.Parameters.put(parameterName, value);
    }

    public void setParameters(DataMapCollection<String, Object> value) {
        this.Parameters = value;
    }
}
