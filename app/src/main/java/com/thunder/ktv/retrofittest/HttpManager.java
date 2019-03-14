package com.thunder.ktv.retrofittest;

import android.content.Context;

import com.thunder.ktv.retrofittest.net.AndroidScheduler;
import com.thunder.ktv.retrofittest.net.HttpObserver;
import com.thunder.ktv.retrofittest.net.ResultMap;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HttpManager {
    private volatile static HttpManager singleton;
    private WeakReference<Context> context;
    private Observable observable;
    private HttpObserver observer;

    private HttpManager() {

    }

    public static HttpManager getInstance() {
        if (singleton == null) {
            synchronized (HttpManager.class) {
                if (singleton == null) {
                    singleton = new HttpManager();
                }
            }
        }
        return singleton;
    }

    public HttpManager with(Context context) {
        this.context = new WeakReference<>(context);
        return singleton;
    }

    public HttpManager setObservable(Observable observable) {
        this.observable = observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidScheduler.mainThread())
                .map(new ResultMap());
        return singleton;
    }

    //创建subscriber
    public HttpManager setDataListener(HttpDataListener listener) {
        observer = new HttpObserver(listener, context.get());
        return singleton;
    }

//    //创建subscriber 自定义ProgressDialog的文字
//    public void setDataListener(HttpDataListener listener, String message) {
//        observable.subscribe(new HttpObserver(listener, context.get(), message));
//    }
//
//    //创建subscriber 自定义ProgressDialog
//    public void setDataListener(HttpDataListener listener, ProgressDialog dialog) {
//        observable.subscribe(new HttpObserver(listener, context.get(), dialog));
//    }

    public void call()
    {
        observable.subscribe(observer);
    }
}
