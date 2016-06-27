package com.microsoft.xrm.sdk.Client;

import java.util.HashMap;

/**
 * Created on 3/12/2015.
 */
public class QueryOptions {

    private HashMap<String, String> queries = new HashMap<String, String>();

    public QueryOptions() {

    }

    public static QueryOptions build() {
        return new QueryOptions();
    }

    HashMap<String, String> getQueryMap() {
        return this.queries;
    }

    public QueryOptions putExpand(String expand) {
        queries.put("$expand", expand);
        return this;
    }

    public QueryOptions putFilter(String filter) {
        queries.put("$filter", filter);
        return this;
    }

    public QueryOptions putOrderBy(String orderby) {
        queries.put("$orderby", orderby);
        return this;
    }

    public QueryOptions putSelect(String select) {
        queries.put("$select", select);
        return this;
    }

    public QueryOptions putSelect(String... select) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String column : select) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(column);
        }
        queries.put("$select", stringBuilder.toString());
        return this;
    }

    public QueryOptions putSkip(String skip) {
        queries.put("$skip", skip);
        return this;
    }

    public QueryOptions putTop(String top) {
        queries.put("$top", top);
        return this;
    }
}
