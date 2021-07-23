package org.jzl.android.recyclerview.core.module;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.listeners.IListenerManager;

class MultipleAdapterModule<T, VH extends IViewHolder> extends MultipleModule<T, VH> implements IAdapterModule<T, VH> {
    MultipleAdapterModule(@NonNull IViewHolderFactory<VH> viewHolderFactory, @NonNull IListenerManager<T, VH> listenerManager) {
        super(viewHolderFactory, listenerManager);
    }
}
