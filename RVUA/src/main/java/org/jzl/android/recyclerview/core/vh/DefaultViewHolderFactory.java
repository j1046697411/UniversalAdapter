package org.jzl.android.recyclerview.core.vh;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;

public class DefaultViewHolderFactory implements IViewHolderFactory<DefaultViewHolder> {

    @NonNull
    @Override
    public DefaultViewHolder createViewHolder(@NonNull IOptions<?, DefaultViewHolder> options, @NonNull View itemView, int itemViewType) {
        return new DefaultViewHolder(itemView, itemViewType);
    }

}
