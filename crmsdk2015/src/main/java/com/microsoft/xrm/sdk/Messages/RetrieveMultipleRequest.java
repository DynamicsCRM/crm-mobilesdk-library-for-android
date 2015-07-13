package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.Query.QueryBase;

/**
 * Created on 3/24/2015.
 */
public final class RetrieveMultipleRequest extends OrganizationRequest {

    public RetrieveMultipleRequest() {
        this.setRequestName("RetrieveMultiple");
        this.setResponseType(new RetrieveMultipleResponse());
        this.setQuery(null);
    }

    public RetrieveMultipleRequest(QueryBase query) {
        this();
        this.setQuery(query);
    }

    @Nullable
    public QueryBase getQuery() {
        if (this.getParameters().containsKey("Query")) {
            return (QueryBase)this.getParameters().get("Query");
        }

        return null;
    }

    public void setQuery(QueryBase value) {
        this.set("Query", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Query", getQuery());
        return getSoapBody();
    }
}
