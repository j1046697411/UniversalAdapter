package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;

public interface OnViewAttachedToWindowListener<T, VH extends IViewHolder> {

    void onViewAttachedToWindow(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> owner);

}
