package org.jzl.android.recyclerview.core;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolderFactory;

public interface IViewHolderFactory<VH extends IViewHolder> {

    DefaultViewHolderFactory DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY = new DefaultViewHolderFactory();

    @NonNull
    VH createViewHolder(@NonNull IOptions<?, VH> options, @NonNull View itemView, int itemViewType);

    static IViewHolderFactory<DefaultViewHolder> of(){
        return DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY;
    }

}
