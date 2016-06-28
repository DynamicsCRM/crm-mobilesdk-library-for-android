package com.microsoft.xrm.sdk.Client.Converters;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public final class SoapRequestConverter implements Converter<String, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("text/xml; charset=utf-8");

    @Override
    public RequestBody convert(String value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, value);
    }

}
