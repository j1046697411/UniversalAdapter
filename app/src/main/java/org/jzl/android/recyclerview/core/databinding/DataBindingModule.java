package org.jzl.android.recyclerview.core.databinding;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.listeners.IBindListener;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.databinding.ItemMovieBinding;

public class DataBindingModule implements IModule<DataBindingModel, DataBindingViewHolder<ItemMovieBinding>> {
    private final Activity activity;
    private final int[] itemViewTypes;

    public DataBindingModule(Activity activity, int... itemViewTypes) {
        this.itemViewTypes = itemViewTypes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public IOptions<DataBindingModel, DataBindingViewHolder<ItemMovieBinding>> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<DataBindingModel> dataGetter) {
        return configuration.options(this, (options, itemView, itemViewType) -> new DataBindingViewHolder<>(itemView), dataGetter)
                .createItemView(R.layout.item_movie, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    viewHolder.getDataBinding().setDataBindingModel(data);
                }, itemViewTypes)
                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.btn_buy), (options, viewHolderOwner) -> {
                    int index = viewHolderOwner.getContext().getAdapterPosition();
                    DataBindingModel model = options.getDataGetter().getData(index);
                    Toast.makeText(activity, "buy " + model.getTitle() + "(" + model.getPrice() + ")", Toast.LENGTH_SHORT).show();
                }, IMatchPolicy.MATCH_POLICY_ALL)
                .build();
    }
}
