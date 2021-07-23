package org.jzl.android.recyclerview.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jzl.android.recyclerview.BuildConfig;

public interface ILogger {

    @NonNull
    static ILogger logger(Class<?> type) {
        return logger(type.getSimpleName());
    }

    static ILogger logger(@NonNull String tag) {
        return BuildConfig.DEBUG ? new AndroidLogger(tag) : new NullableLogger();
    }

    int verbose(@NonNull String msg);

    int verbose(@Nullable String msg, @Nullable Throwable tr);

    int debug(@NonNull String msg);

    int debug(@Nullable String msg, @Nullable Throwable tr);

    int info(@NonNull String msg);

    int info(@Nullable String msg, @Nullable Throwable tr);

    int warn(@NonNull String msg);

    int warn(@Nullable String msg, @Nullable Throwable tr);

    int warn(@Nullable Throwable tr);

    int error(@NonNull String msg);

    int error(@Nullable String msg, @Nullable Throwable tr);

}
