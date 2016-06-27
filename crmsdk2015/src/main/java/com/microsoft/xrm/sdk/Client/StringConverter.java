package com.microsoft.xrm.sdk.Client;

import java.lang.reflect.Type;
import java.util.Scanner;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

class StringConverter implements Converter {

    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        try {
            Scanner scanner = new Scanner(typedInput.in()).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
            return null;
        }
    }

    @Override
    public TypedOutput toBody(Object o) {
        return null;
    }
}