package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;

@FunctionalInterface
public interface OnCreatedViewHolderListener<T, VH extends IViewHolder> {

    void onCreatedViewHolder(IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner);

}
