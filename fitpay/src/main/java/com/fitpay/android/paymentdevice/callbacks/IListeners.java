package com.fitpay.android.paymentdevice.callbacks;

import com.fitpay.android.api.models.apdu.ApduExecutionResult;
import com.fitpay.android.api.models.device.Commit;
import com.fitpay.android.api.models.device.Device;
import com.fitpay.android.paymentdevice.enums.Connection;
import com.fitpay.android.paymentdevice.enums.Sync;

/**
 * Collection of payment device callbacks
 */
public final class IListeners {

    public interface ApduListener {
        void onApduPackageResultReceived(final ApduExecutionResult result);
        void onApduPackageErrorReceived(final ApduExecutionResult result);
    }

    public interface ConnectionListener {
        void onDeviceStateChanged(final @Connection.State int state);
    }

    public interface SyncListener {
        void onSyncStateChanged(final Sync syncEvent);
        void onNonApduCommit(final Commit commit);
    }

    public interface PaymentDeviceListener extends ConnectionListener {
        void onDeviceInfoReceived(final Device device);
        void onNFCStateReceived(final boolean isEnabled, final byte errorCode);
        void onNotificationReceived(final byte[] data);
        void onApplicationControlReceived(final byte[] data);
    }
}
