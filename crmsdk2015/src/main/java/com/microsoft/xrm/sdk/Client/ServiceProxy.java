package com.microsoft.xrm.sdk.Client;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.Retrofit;

public abstract class ServiceProxy {

    private String sessionToken;
    private String endpoint;
    private Interceptor authHeader;

    protected ServiceProxy(@NonNull String endpoint, @NonNull final String sessionToken) {
        this.endpoint = endpoint;
        this.sessionToken = sessionToken;
        this.authHeader = buildNewInterceptor(null);
    }

    @NonNull
    private Interceptor buildNewInterceptor(@Nullable final ArrayMap<String, String> headers) {
        return chain -> {
            Request request = chain.request();

            Request.Builder builder = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + sessionToken.replaceAll("(\\r|\\n)", ""));

            if (headers != null) {
                int count = headers.size();
                for (int i = 0; i < count; i++) {
                    builder.addHeader(headers.keyAt(i), headers.valueAt(i));
                }
            }

            return chain.proceed(builder.build());
        };
    }

    void addGlobalHeaders(@NonNull final ArrayMap<String, String> headers) {
        this.authHeader = buildNewInterceptor(headers);
    }

    @NonNull
    private OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(authHeader)
                .build();
    }

    <T> T buildService(Converter.Factory factory, @NonNull Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(buildClient())
                .baseUrl(endpoint)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(factory)
                .build();

        return retrofit.create(serviceClass);
    }
}
