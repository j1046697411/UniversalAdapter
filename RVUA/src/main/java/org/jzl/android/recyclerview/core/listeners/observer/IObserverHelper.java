package org.jzl.android.recyclerview.core.listeners.observer;

import androidx.annotation.NonNull;

public interface IObserverHelper<T, OB> {

    @NonNull
    OB createObserver(@NonNull T target, @NonNull IForeach<OB> foreach);

    void registeredObserver(@NonNull T target, @NonNull OB observer);

    void unregisteredObserver(@NonNull T target, @NonNull OB listener);
}
