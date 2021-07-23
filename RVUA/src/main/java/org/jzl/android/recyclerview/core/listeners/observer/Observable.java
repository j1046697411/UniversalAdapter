package org.jzl.android.recyclerview.core.listeners.observer;

import androidx.annotation.NonNull;

import org.jzl.lang.fun.Consumer;
import org.jzl.lang.util.ForeachUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class Observable<T, OB> implements IObservable<OB>, IForeach<OB> {

    private final T target;
    private final List<OB> observers = new CopyOnWriteArrayList<>();
    private final IObserverHelper<T, OB> observerHelper;
    private final OB listener;

    Observable(T target, IObserverHelper<T, OB> observerHelper) {
        this.target = target;
        this.observerHelper = observerHelper;
        listener = observerHelper.createObserver(target, this);
        observerHelper.registeredObserver(target, listener);
    }

    @Override
    public void observe(@NonNull OB observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unobserved(@NonNull OB observer) {
        observers.remove(observer);
    }

    @Override
    public void clear() {
        observers.clear();
        observerHelper.unregisteredObserver(target, listener);
    }

    @Override
    public void each(@NonNull Consumer<OB> target) {
        ForeachUtils.each(this.observers, target);
    }

}
