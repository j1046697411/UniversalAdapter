package org.jzl.android.recyclerview.core;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.util.ViewBinder;

public interface IViewHolderOwner<VH extends IViewHolder> {

    @NonNull
    IContext getContext();

    @NonNull
    View getItemView();

    @NonNull
    VH getViewHolder();

    @NonNull
    ViewBinder getViewBinder();

}
