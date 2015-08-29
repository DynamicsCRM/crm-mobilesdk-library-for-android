package com.microsoft.xrm.sdk.Query;

import com.microsoft.xrm.sdk.Utils;

/**
 * Created on 3/6/2015.
 */
public final class FetchExpression extends QueryBase {
    private String query;

    public FetchExpression(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String value) {
        this.query = value;
    }

    public String toValueXml() {
        return Utils.objectToXml(query, "a:Query", true);
    }
}
