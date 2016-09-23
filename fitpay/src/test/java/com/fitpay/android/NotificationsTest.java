package com.fitpay.android;

import com.fitpay.android.paymentdevice.callbacks.ConnectionListener;
import com.fitpay.android.paymentdevice.constants.States;
import com.fitpay.android.paymentdevice.enums.Connection;
import com.fitpay.android.utils.Command;
import com.fitpay.android.utils.Listener;
import com.fitpay.android.utils.NotificationManager;
import com.fitpay.android.utils.RxBus;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationsTest {

    private static Listener listener;
    private static NotificationManager manager;

    private static List<Listener> listeners;
    private static ConcurrentHashMap<Class, Subscription> subscriptions;
    private static ConcurrentHashMap<Class, List<Command>> commands;

    private static
    @Connection.State
    Integer testState;

    @BeforeClass
    public static void init() {
        RxAndroidPlugins.getInstance().reset();

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        listener = new ConnectionListener() {
            @Override
            public void onDeviceStateChanged(@Connection.State int state) {
                testState = state;
            }
        };

        manager = NotificationManager.getInstance();

        listeners = (List<Listener>) getPrivateField(manager, "mListeners");
        subscriptions = (ConcurrentHashMap<Class, Subscription>) getPrivateField(manager, "mSubscriptions");
        commands = (ConcurrentHashMap<Class, List<Command>>) getPrivateField(manager, "mCommands");
    }

    @Test
    public void test01_checkManager() throws InterruptedException {
        Assert.assertNotNull(listeners);
        Assert.assertNotNull(subscriptions);
        Assert.assertNotNull(commands);
    }

    @Test
    public void test02_addListener() throws InterruptedException {
        manager.addListener(listener);

        Assert.assertEquals(1, listeners.size());
        Assert.assertEquals(1, subscriptions.size());
        Assert.assertEquals(1, commands.size());
    }

    @Test
    @Ignore   // TODO determine why connection event is not being posted on the bus - this used to work
    public void test03_checkNotification() throws InterruptedException {
        AtomicBoolean changed = new AtomicBoolean(false);

        Observable.create(subscriber -> {
            RxBus.getInstance().post(new Connection(States.CONNECTED));

            subscriber.onNext(null);
            subscriber.onCompleted();
        })
        .observeOn(AndroidSchedulers.mainThread())
        .toBlocking()
        .subscribe(o -> {}, e -> {}, () -> {
            if (testState != null && testState == States.CONNECTED) {
                changed.set(true);
            }
        });

        Assert.assertTrue("state was not changed", changed.get());
    }

    @Test
    public void test04_removeListener() throws InterruptedException {
        manager.removeListener(listener);

        Assert.assertEquals(0, listeners.size());
        Assert.assertEquals(0, subscriptions.size());
        Assert.assertEquals(0, commands.size());
    }


    private static Object getPrivateField(Object from, String fieldName) {
        try {
            Field field = from.getClass().getDeclaredField(fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(from);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    @AfterClass
    public static void tearDown() throws Exception {

        manager.removeListener(listener);

        listener = null;
        manager = null;
        listeners = null;
        subscriptions = null;
        commands = null;

        RxAndroidPlugins.getInstance().reset();
    }
}
