package org.jzl.android.recyclerview.modules;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import org.jzl.android.recyclerview.BR;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.listeners.IBindListener;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.databinding.LayoutClickBookBinding;
import org.jzl.android.recyclerview.databinding.ViewDataBindingViewHolder;
import org.jzl.android.recyclerview.model.Book;

public class ClickBookModule implements IModule<Book, ViewDataBindingViewHolder<LayoutClickBookBinding>> {

    public static final int ITEM_VIEW_TYPE_CLICK_BOOK = 2;
    private final LifecycleOwner lifecycleOwner;

    public ClickBookModule(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public IOptions<Book, ViewDataBindingViewHolder<LayoutClickBookBinding>> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Book> dataGetter) {
        return configuration.options(this, (options, itemView, itemViewType) -> new ViewDataBindingViewHolder<>(itemView, lifecycleOwner), dataGetter)
                .createItemView(R.layout.layout_click_book, ITEM_VIEW_TYPE_CLICK_BOOK)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    viewHolder.setVariable(BR.book, data);
                }, ITEM_VIEW_TYPE_CLICK_BOOK)
                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.btn_click), (options, viewHolderOwner) -> {
                    Toast.makeText(viewHolderOwner.getItemView().getContext(), "点击了 ->" + viewHolderOwner.getContext().getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }, IBindPolicy.ofItemViewTypes(ITEM_VIEW_TYPE_CLICK_BOOK))
                .build();
    }
}
