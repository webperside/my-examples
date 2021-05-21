package com.webperside.smppclientexample.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final AtomicInteger sequence = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("SmppClientSessionWindowMonitorPool-" + sequence.getAndIncrement());
        return thread;
    }
}
