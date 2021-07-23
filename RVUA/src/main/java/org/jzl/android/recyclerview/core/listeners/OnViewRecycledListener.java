package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;

public interface OnViewRecycledListener<T, VH extends IViewHolder> {

    void onViewRecycled(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner);

}
