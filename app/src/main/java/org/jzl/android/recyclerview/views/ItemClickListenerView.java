package org.jzl.android.recyclerview.views;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.listeners.proxy.ClickAnimationProxy;
import org.jzl.android.recyclerview.core.listeners.proxy.LongClickAnimatorProxy;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.model.Book;
import org.jzl.android.recyclerview.util.BookUtils;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;
import org.jzl.android.recyclerview.vm.BookModule;

public class ItemClickListenerView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public ItemClickListenerView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        activityRevyclerViewBinding.tvMsg.setText(R.string.click_me);
        DataBlockProvider<Book> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        BookUtils.randomFillingBooks(dataBlockProvider, 50);
        IConfiguration.<Book, IViewHolder>builder((options, itemView, itemViewType) -> new DefaultViewHolder(itemView, itemViewType))
                .setDataProvider(dataBlockProvider)
                .registered(new BookModule(parentView))
                .addClickItemViewListener(ClickAnimationProxy.zoom(0.8f, (options, viewHolderOwner) -> {
                    Toast.makeText(getApplication(), "click => " + viewHolderOwner.getContext().getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }), context -> context.getAdapterPosition() % 2 == 0)
                .addLongClickItemViewListener(LongClickAnimatorProxy.zoom(0.8f, (options, viewHolderOwner) -> {
                    Toast.makeText(getApplication(), "longClick => " + viewHolderOwner.getContext().getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    return true;
                }), context -> context.getAdapterPosition() % 2 == 1)
                .build(recyclerView);
    }
}
