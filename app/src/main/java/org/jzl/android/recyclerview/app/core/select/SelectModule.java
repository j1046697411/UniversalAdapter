package org.jzl.android.recyclerview.app.core.select;

import androidx.annotation.NonNull;
import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.app.model.Card;

public class SelectModule implements IModule<Card, SelectViewHolder> {

    private final IExtendView<?, ?, ?> parent;
    private final int[] itemViewTypes;

    public SelectModule(@NonNull IExtendView<?, ?, ?> parent, int... itemViewTypes) {
        this.parent = parent;
        this.itemViewTypes = itemViewTypes;
    }

    @NonNull
    @Override
    public IOptions<Card, SelectViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Card> dataGetter) {
        return configuration.options(this, (options, itemView, itemViewType) -> new SelectViewHolder(parent, itemView), dataGetter)
                .createItemView(R.layout.item_section_content, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> viewHolder.bindCard(data), itemViewTypes)
                .build();
    }
}
