package org.jzl.android.recyclerview.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class NullableLogger implements ILogger {
    @Override
    public int verbose(@NonNull String msg) {
        return 0;
    }

    @Override
    public int verbose(@Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    @Override
    public int debug(@NonNull String msg) {
        return 0;
    }

    @Override
    public int debug(@Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    @Override
    public int info(@NonNull String msg) {
        return 0;
    }

    @Override
    public int info(@Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    @Override
    public int warn(@NonNull String msg) {
        return 0;
    }

    @Override
    public int warn(@Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    @Override
    public int warn(@Nullable Throwable tr) {
        return 0;
    }

    @Override
    public int error(@NonNull String msg) {
        return 0;
    }

    @Override
    public int error(@Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }
}
