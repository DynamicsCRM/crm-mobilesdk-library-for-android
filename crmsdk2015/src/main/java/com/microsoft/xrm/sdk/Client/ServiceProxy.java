package com.microsoft.xrm.sdk.Client;

import retrofit.RequestInterceptor;

/**
 * Created on 3/6/2015.
 */
public abstract class ServiceProxy {

    private String endpoint;
    private RequestInterceptor authHeader;

    protected ServiceProxy(String endpoint, RequestInterceptor requestInterceptor) {
        this.endpoint = endpoint;
        this.authHeader = requestInterceptor;
    }

    protected ServiceProxy(String endpoint, final String sessionToken) {
        this.endpoint = endpoint;
        this.authHeader = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Bearer " + sessionToken.replaceAll("(\\r|\\n)", ""));
            }
        };
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public RequestInterceptor getAuthHeader() {
        return this.authHeader;
    }
}
