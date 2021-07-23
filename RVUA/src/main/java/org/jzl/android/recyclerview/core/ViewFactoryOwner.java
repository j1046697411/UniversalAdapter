package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;

class ViewFactoryOwner<T, VH extends IViewHolder> implements IViewFactoryOwner<VH> {

    @NonNull
    private final IOptions<T, VH> options;
    @NonNull
    private final IViewFactory viewFactory;
    @NonNull
    private final IMatchPolicy matchPolicy;
    private final int priority;

    ViewFactoryOwner(@NonNull IOptions<T, VH> options, @NonNull IViewFactory viewFactory, @NonNull IMatchPolicy matchPolicy, int priority) {
        this.options = options;
        this.viewFactory = viewFactory;
        this.matchPolicy = matchPolicy;
        this.priority = priority;
    }

    @NonNull
    @Override
    public IOptions<T, VH> getOptions() {
        return options;
    }

    @NonNull
    @Override
    public IMatchPolicy getMatchPolicy() {
        return matchPolicy;
    }

    @NonNull
    @Override
    public IViewFactory getViewFactory() {
        return viewFactory;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
