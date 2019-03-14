package com.thunder.ktv.retrofittest;

public interface HttpDataListener<T> {
    void onNext(T t);
}
