package org.jzl.android.recyclerview.core.listeners.observer;

import androidx.annotation.NonNull;

import org.jzl.lang.fun.Consumer;

@FunctionalInterface
public interface IForeach<T> {

    void each(@NonNull Consumer<T> target);
}
