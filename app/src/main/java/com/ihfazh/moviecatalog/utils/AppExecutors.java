package com.ihfazh.moviecatalog.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private Executor mainThread; // biasanya digunakan untuk get datanya
    private Executor diskIO; // untuk ketika masukkan data
    private Executor networkIO;

    public AppExecutors(Executor mainThread, Executor diskIO, Executor networkIO) {
        this.mainThread = mainThread;
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    public AppExecutors() {
        this(
                new MainThreadExecutor(),
                Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3)
        );
    }

    private static class MainThreadExecutor implements Executor {
        Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }
}
