package com.microsoft.xrm.sdk;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created on 3/6/2015.
 */
public class DataMapCollection<TKey, TValue> implements Map<TKey, TValue> {

    private Map<TKey, TValue> mapCollection = new HashMap<TKey, TValue>();
    private boolean isReadOnly;

    DataMapCollection() {

    }

    @Override
    public void clear() {
        try {
            this.checkIsReadOnly();
            mapCollection.clear();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return mapCollection.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return mapCollection.containsValue(value);
    }

    @NonNull
    @Override
    public Set<Entry<TKey, TValue>> entrySet() {
        return mapCollection.entrySet();
    }

    @Override
    public TValue get(Object key) {
        if (mapCollection.containsKey(key)) {
            return mapCollection.get(key);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return mapCollection.isEmpty();
    }

    @NonNull
    @Override
    public Set<TKey> keySet() {
        return mapCollection.keySet();
    }

    @Override
    public TValue put(TKey key, TValue value) {
        try {
            this.checkIsReadOnly();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this.mapCollection.put(key, value);
    }

    @Override
    public void putAll(Map<? extends TKey, ? extends TValue> map) {
        try {
            this.checkIsReadOnly();
            mapCollection.putAll(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TValue remove(Object key) {
        try {
            this.checkIsReadOnly();
            return mapCollection.remove(key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int size() {
        return mapCollection.size();
    }

    @NonNull
    @Override
    public Collection<TValue> values() {
        return mapCollection.values();
    }

    public boolean getIsReadOnly() {
        return this.isReadOnly;
    }

    public void setIsReadOnly(boolean value) {
        this.isReadOnly = value;
    }

    void SetItemInternal(TKey key, TValue value)
    {
        this.mapCollection.put(key, value);
    }

    void ClearInternal()
    {
        this.mapCollection.clear();
    }

    TValue RemoveInternal(TKey key)
    {
        return this.mapCollection.remove(key);
    }

    private void checkIsReadOnly() throws IllegalAccessException {
        if (this.isReadOnly) {
            throw new IllegalAccessException("The collection is read-only.");
        }
    }
}
