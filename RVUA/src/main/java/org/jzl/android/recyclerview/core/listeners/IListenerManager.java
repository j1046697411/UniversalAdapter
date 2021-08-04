package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;

public interface IListenerManager<T, VH extends IViewHolder> extends IListenerManagerBuilder<T, VH, IListenerManager<T, VH>> {

    void notifyCreatedViewHolder(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner, int viewType);

    void notifyViewAttachedToWindow(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner);

    void notifyViewDetachedFromWindow(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner);

    void notifyAttachedToRecyclerView(@NonNull RecyclerView recyclerView, @NonNull IOptions<T, VH> options);

    void notifyDetachedFromRecyclerView(@NonNull RecyclerView recyclerView, @NonNull IOptions<T, VH> options);

    void notifyViewRecycled(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner);

}
