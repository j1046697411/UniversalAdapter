package org.jzl.android.recyclerview.app.core.header;

import androidx.annotation.NonNull;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.module.IModule;

public class HeaderFooterModule implements IModule<HeaderFooterModel, HeaderFooterViewHolder> {

    private final IExtendView<?, ?, ?> parentContainerView;
    private final int[] itemViewTypes;

    public HeaderFooterModule(@NonNull IExtendView<?, ?, ?> parentContainerView, int... itemViewTypes) {
        this.parentContainerView = parentContainerView;
        this.itemViewTypes = itemViewTypes;
    }

    @NonNull
    @Override
    public IOptions<HeaderFooterModel, HeaderFooterViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<HeaderFooterModel> dataGetter) {
        return configuration.options(this, (options, itemView, itemViewType) -> new HeaderFooterViewHolder(parentContainerView, itemView), dataGetter)
                .createItemView(R.layout.def_section_head, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> viewHolder.bindHeader(data), itemViewTypes)
                .build();
    }
}
