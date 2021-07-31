package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.databinding.DataBindingModel;
import org.jzl.android.recyclerview.core.databinding.DataBindingModule;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.databinding.ItemMovieBinding;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;
import java.util.Random;

public class DataBindingView2 extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public DataBindingView2(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<DataBindingModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);

        IConfiguration
                .<DataBindingModel, DataBindingModule.DataBindingViewHolder<ItemMovieBinding>>builder((options, itemView, itemViewType) -> new DataBindingModule.DataBindingViewHolder<>(itemView))
                .createItemView(R.layout.item_movie)
                .setDataProvider(dataBlockProvider)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    viewHolder.getDataBinding().setDataBindingModel(data);
                })
                .build(recyclerView);
    }

    public void add(Collection<DataBindingModel> dataBindingModels){
        Random random = new Random();
        for (int i = 0; i < 100; i ++){
            dataBindingModels.add(new DataBindingModel(R.mipmap.databinding_img, "card", 0, "This card is exciting", random.nextInt(5) + 5));
        }

    }
}
