package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;

public interface IViewHolderFactoryOwner<VH extends IViewHolder> {

    @NonNull
    IViewHolderFactory<VH> getViewHolderFactory();

}
