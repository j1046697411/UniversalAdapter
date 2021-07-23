package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;

public interface OnViewDetachedFromWindowListener<T, VH extends IViewHolder> {

    void onViewDetachedFromWindow(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> owner);

}
