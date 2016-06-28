package com.microsoft.xrm.sdk;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DataCollection<T> implements Collection<T> {

    private ArrayList<T> dataCollection = new ArrayList<>();

    DataCollection() {

    }

    DataCollection(List<T> list) {
        dataCollection.addAll(list);
    }

    public boolean addRange(T[] items) {
        return items != null && dataCollection.addAll(Arrays.asList(items));
    }

    @Override
    public boolean add(T object) {
        return dataCollection.add(object);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> collection) {
        return dataCollection.addAll(collection);
    }

    @Override
    public void clear() {
        dataCollection.clear();
    }

    @Override
    public boolean contains(Object object) {
        return dataCollection.contains(object);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return dataCollection.containsAll(collection);
    }

    @Override
    public boolean isEmpty() {
        return dataCollection.isEmpty();
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return dataCollection.iterator();
    }

    @Override
    public boolean remove(Object object) {
        return dataCollection.remove(object);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return dataCollection.removeAll(collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return dataCollection.retainAll(collection);
    }

    @Override
    public int size() {
        return dataCollection.size();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return dataCollection.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(@NonNull T1[] array) {
        return dataCollection.toArray(array);
    }

    public T get(int index) {
        return this.dataCollection.get(index);
    }

    public <T> Iterable<T> toEntities(Class<?> T) {

        return new ArrayList<>();
    }
}

