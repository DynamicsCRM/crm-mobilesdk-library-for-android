package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.Query.QueryBase;

/**
 * Created on 3/26/2015.
 */
public final class BackgroundSendEmailRequest extends OrganizationRequest {

    public BackgroundSendEmailRequest() {
        this.setResponseType(new BackgroundSendEmailResponse());
        this.setRequestName("BackgroundSendEmail");
    }

    @Nullable
    public QueryBase getQuery() {
        if (this.getParameters().containsKey("Query")) {
            return (QueryBase) this.getParameters().get("Query");
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
