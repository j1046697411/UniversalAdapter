package org.jzl.android.recyclerview.util;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class AndroidLogger implements ILogger {

    private final String tag;

    public AndroidLogger(@NonNull String tag) {
        this.tag = tag;
    }

    public int verbose(@NonNull String msg) {
        return Log.v(tag, msg);
    }

    public int verbose(@Nullable String msg, @Nullable Throwable tr) {
        return Log.v(tag, msg, tr);
    }

    public int debug(@NonNull String msg) {
        return Log.d(tag, msg);
    }

    public int debug(@Nullable String msg, @Nullable Throwable tr) {
        return Log.d(tag, msg, tr);
    }

    public int info(@NonNull String msg) {
        return Log.i(tag, msg);
    }

    public int info(@Nullable String msg, @Nullable Throwable tr) {
        return Log.i(tag, msg, tr);
    }

    public int warn(@NonNull String msg) {
        return Log.w(tag, msg);
    }

    public int warn(@Nullable String msg, @Nullable Throwable tr) {
        return Log.w(tag, msg, tr);
    }

    public int warn(@Nullable Throwable tr) {
        return Log.w(tag, tr);
    }

    public int error(@NonNull String msg) {
        return Log.e(tag, msg);
    }

    public int error(@Nullable String msg, @Nullable Throwable tr) {
        return Log.e(tag, msg, tr);
    }
}
