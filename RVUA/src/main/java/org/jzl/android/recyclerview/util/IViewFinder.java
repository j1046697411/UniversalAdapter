package org.jzl.android.recyclerview.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

@FunctionalInterface
public interface IViewFinder {

    <V extends View> V findViewById(@IdRes int id);

    @NonNull
    default ViewBinder binder() {
        return new ViewBinder(cache());
    }

    @NonNull
    default IViewFinder cache() {
        return new CacheViewFinder(this);
    }

    @NonNull
    static IViewFinder of(@NonNull Activity activity) {
        return activity::findViewById;
    }

    @NonNull
    static IViewFinder of(@NonNull Dialog dialog) {
        return dialog::findViewById;
    }

    @NonNull
    static IViewFinder of(@NonNull View view) {
        return view::findViewById;
    }

}