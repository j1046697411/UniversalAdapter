package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IViewHolder;

public interface IListenerManagerOwner<T, VH extends IViewHolder> {

    @NonNull
    IListenerManager<T, VH> getListenerManager();

}
