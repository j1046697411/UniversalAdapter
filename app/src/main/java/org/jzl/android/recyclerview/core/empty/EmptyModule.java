package org.jzl.android.recyclerview.core.empty;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.listeners.OnClickItemViewListener;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;

public class EmptyModule<T> implements IModule<T, DefaultViewHolder> {

    private final OnClickItemViewListener<T, DefaultViewHolder> clickItemViewListener;
    private final int itemViewType;

    public EmptyModule(OnClickItemViewListener<T, DefaultViewHolder> clickItemViewListener, int itemViewType) {
        this.clickItemViewListener = clickItemViewListener;
        this.itemViewType = itemViewType;
    }

    @NonNull
    @Override
    public IOptions<T, DefaultViewHolder> setup(@NonNull  IConfiguration<?, ?> configuration, @NonNull  IDataGetter<T> dataGetter) {
        return configuration.options(this, IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY, dataGetter)
                .createItemView(R.layout.item_loading_view, itemViewType)
                .addClickItemViewListener(clickItemViewListener, itemViewType)
                .build();
    }
}
