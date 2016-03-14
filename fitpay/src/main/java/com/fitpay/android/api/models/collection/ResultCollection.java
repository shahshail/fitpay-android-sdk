package com.fitpay.android.api.models.collection;


import android.support.annotation.NonNull;

import com.fitpay.android.api.callbacks.ApiCallback;

class ResultCollection<T> extends ResultCollectionModel<T>{
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String NEXT = "next";
    private static final String PREV = "prev";

    public void getFirst(@NonNull ApiCallback<? extends ResultCollection<T>> callback) {
        makeGetCall(FIRST, null, this.getClass(), callback);
    }

    public void getLast(@NonNull ApiCallback<? extends ResultCollection<T>> callback) {
        makeGetCall(LAST, null, this.getClass(), callback);
    }

    public void getNext(@NonNull ApiCallback<? extends ResultCollection<T>> callback) {
        makeGetCall(NEXT, null, this.getClass(), callback);
    }

    public void getPrev(@NonNull ApiCallback<? extends ResultCollection<T>> callback) {
        makeGetCall(PREV, null, this.getClass(), callback);
    }
}
