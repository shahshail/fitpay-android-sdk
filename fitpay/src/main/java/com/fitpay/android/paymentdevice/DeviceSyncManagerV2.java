package com.fitpay.android.paymentdevice;

import android.content.Context;

import com.fitpay.android.api.ApiManager;
import com.fitpay.android.paymentdevice.callbacks.DeviceSyncManagerCallback;
import com.fitpay.android.paymentdevice.models.SyncRequest;
import com.fitpay.android.paymentdevice.utils.sync.SyncThreadExecutor;
import com.fitpay.android.utils.FPLog;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Device sync manager v2 can work with multiple devices
 */
public class DeviceSyncManagerV2 {
    private final Context mContext;

    private final BlockingQueue<Runnable> requests;
    private final List<DeviceSyncManagerCallback> syncManagerCallbacks = new CopyOnWriteArrayList<>();

    private int queueSize;
    private int threadsCount;

    private SyncThreadExecutor worker;

    public DeviceSyncManagerV2(Context context) {
        this.mContext = context;
        queueSize = Integer.parseInt(ApiManager.getConfig().get(ApiManager.PROPERTY_SYNC_QUEUE_SIZE));
        threadsCount = Integer.parseInt(ApiManager.getConfig().get(ApiManager.PROPERTY_SYNC_THREADS_COUNT));
        requests = new ArrayBlockingQueue<>(queueSize);
    }

    public void onCreate() {
        worker = new SyncThreadExecutor(mContext, syncManagerCallbacks, queueSize, threadsCount, 5, TimeUnit. MINUTES, requests);
    }

    public void onDestroy() {
        if (requests != null) {
            requests.clear();
        }

        if (worker != null) {
            worker.shutdownNow();
        }
    }

    public void add(final SyncRequest request) {
        if (request == null) {
            return;
        }

        worker.addTask(request);

        FPLog.d("added sync request to queue for processing, current queue size [" + requests.size() + "]: " + request);

        for (DeviceSyncManagerCallback callback : syncManagerCallbacks) {
            callback.syncRequestAdded(request);
        }
    }

    public void removeDeviceSyncManagerCallback(DeviceSyncManagerCallback syncManagerCallback) {
        if (syncManagerCallback == null) {
            return;
        }

        syncManagerCallbacks.remove(syncManagerCallback);
    }

    public void registerDeviceSyncManagerCallback(DeviceSyncManagerCallback syncManagerCallback) {
        if (syncManagerCallback == null) {
            return;
        }

        syncManagerCallbacks.add(syncManagerCallback);
    }
}
