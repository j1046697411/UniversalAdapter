package org.jzl.android.recyclerview.core.listeners;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;

public interface IBindListener<VH extends IViewHolder, L> {

    void bind(@NonNull IViewHolderOwner<VH> owner, @NonNull L listener);

    @NonNull
    static <VH extends IViewHolder> IBindListener<VH, View.OnClickListener> ofClicks(int... ids) {
        return (owner, listener) -> owner.getViewBinder().addClickListeners(listener, ids);
    }

    @NonNull
    static <VH extends IViewHolder> IBindListener<VH, View.OnLongClickListener> ofLongClick(int... ids) {
        return (owner, listener) -> owner.getViewBinder().addLongClickListeners(listener, ids);
    }
}
