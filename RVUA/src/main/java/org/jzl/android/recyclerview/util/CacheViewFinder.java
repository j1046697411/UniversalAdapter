package org.jzl.android.recyclerview.util;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.lang.util.ObjectUtils;

class CacheViewFinder implements IViewFinder {

    private final IViewFinder viewFinder;
    private final SparseArray<View> cacheViews = new SparseArray<>();

    CacheViewFinder(IViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(int id) {
        View view = cacheViews.get(id);
        if (ObjectUtils.isNull(view)) {
            view = viewFinder.findViewById(id);
            cacheViews.put(id, view);
        }
        return (V) view;
    }

    @NonNull
    @Override
    public IViewFinder cache() {
        return this;
    }
}
