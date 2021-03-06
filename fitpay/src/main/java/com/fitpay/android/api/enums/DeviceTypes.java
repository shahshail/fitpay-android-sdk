package com.fitpay.android.api.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Device types enum
 */
public final class DeviceTypes {
    public static final String ACTIVITY_TRACKER = "ACTIVITY_TRACKER";
    public static final String MOCK = "MOCK";
    public static final String SMART_STRAP = "SMART_STRAP";
    public static final String WATCH = "WATCH";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ACTIVITY_TRACKER,
            MOCK,
            SMART_STRAP,
            WATCH
    })
    public @interface Type{}
}
