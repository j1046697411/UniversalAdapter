package org.jzl.android.recyclerview.core.header;

import androidx.annotation.NonNull;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.module.IModule;

public class HeaderModule implements IModule<Header, HeaderViewHolder> {

    private final IExtendView<?, ?, ?> parentContainerView;
    private final int[] itemViewTypes;

    public HeaderModule(@NonNull IExtendView<?, ?, ?> parentContainerView, int... itemViewTypes) {
        this.parentContainerView = parentContainerView;
        this.itemViewTypes = itemViewTypes;
    }

    @NonNull
    @Override
    public IOptions<Header, HeaderViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Header> dataGetter) {
        return configuration.options(this, (options, itemView, itemViewType) -> new HeaderViewHolder(parentContainerView, itemView), dataGetter)
                .createItemView(R.layout.def_section_head, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> viewHolder.bindHeader(data), itemViewTypes)
                .build();
    }
}
