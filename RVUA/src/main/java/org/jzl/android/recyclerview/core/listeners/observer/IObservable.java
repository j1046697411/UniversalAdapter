package org.jzl.android.recyclerview.core.listeners.observer;

import android.view.View;

import androidx.annotation.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;

public interface IObservable<OB> {

    void observe(@NonNull OB observer);

    void unobserved(@NonNull OB observer);

    void clear();

    static <T, OB> IObservable<OB> of(@NonNull T target, @NonNull IObserverHelper<T, OB> observerHelper) {
        return new Observable<>(target, observerHelper);
    }

    static IObservable<View.OnClickListener> click(@NonNull View view) {
        return of(view, new IObserverHelper<View, View.OnClickListener>() {
            @NonNull
            @Override
            public View.OnClickListener createObserver(@NonNull View target, @NonNull IForeach<View.OnClickListener> foreach) {
                return v -> foreach.each(target1 -> target1.onClick(v));
            }

            @Override
            public void registeredObserver(@NonNull View target, @NonNull View.OnClickListener observer) {
                target.setOnClickListener(observer);
            }

            @Override
            public void unregisteredObserver(@NonNull View target, @NonNull View.OnClickListener listener) {
                target.setOnClickListener(null);
            }
        });
    }

    static IObservable<View.OnLongClickListener> longClick(@NonNull View view) {

        return of(view, new IObserverHelper<View, View.OnLongClickListener>() {

            @NonNull
            @Override
            public View.OnLongClickListener createObserver(@NonNull View target, @NonNull IForeach<View.OnLongClickListener> foreach) {
                return v -> {
                    AtomicBoolean longClick = new AtomicBoolean(false);
                    foreach.each(target1 -> {
                        if (target1.onLongClick(v)) {
                            longClick.set(true);
                        }
                    });
                    return longClick.get();
                };
            }

            @Override
            public void registeredObserver(@NonNull View target, @NonNull View.OnLongClickListener observer) {
                target.setOnLongClickListener(observer);
            }

            @Override
            public void unregisteredObserver(@NonNull View target, @NonNull View.OnLongClickListener listener) {
                target.setOnLongClickListener(null);
            }
        });
    }

}
