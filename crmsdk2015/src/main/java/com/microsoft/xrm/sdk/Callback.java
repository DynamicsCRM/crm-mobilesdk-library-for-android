package com.microsoft.xrm.sdk;


public interface Callback<T> {

    void success(T t);

    void failure(RetrofitError error);

}
