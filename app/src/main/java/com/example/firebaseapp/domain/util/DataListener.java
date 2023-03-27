package com.example.firebaseapp.domain.util;

public interface DataListener<T> {

    void onChange(T value);
}
