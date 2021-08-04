package org.jzl.android.recyclerview.app.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.core.databinding.DataBindingModel;
import org.jzl.android.recyclerview.app.core.databinding.DataBindingModule;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;
import java.util.Random;

public class DataBindingView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public DataBindingView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<DataBindingModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);
        IConfiguration.<DataBindingModel, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .registered(new DataBindingModule(parentView, 1))
                .build(recyclerView);

    }

    public void add(Collection<DataBindingModel> dataBindingModels){
        Random random = new Random();
        for (int i = 0; i < 100; i ++){
            dataBindingModels.add(new DataBindingModel(R.mipmap.databinding_img, "card", 0, "This card is exciting", random.nextInt(5) + 5));
        }

    }
}
