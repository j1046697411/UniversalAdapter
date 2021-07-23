package org.jzl.android.recyclerview;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import org.jzl.lang.util.ObjectUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UniversalGlobal {

    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @NonNull
    public UniversalGlobal setMainHandler(Handler mainHandler) {
        this.mainHandler = ObjectUtils.get(mainHandler, this.mainHandler);
        return this;
    }

    @NonNull
    public UniversalGlobal setExecutorService(ExecutorService executorService) {
        this.executorService = ObjectUtils.get(executorService, this.executorService);
        return this;
    }

    @NonNull
    public ExecutorService getExecutorService() {
        return executorService;
    }

    @NonNull
    public Handler getMainHandler() {
        return mainHandler;
    }

    public static UniversalGlobal getInstance() {
        return Holder.SIN;
    }

    private static class Holder {
        private static final UniversalGlobal SIN = new UniversalGlobal();
    }
}
