package org.jzl.android.recyclerview.core.home;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.listeners.proxy.ClickAnimationProxy;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;

public class HomeItemModule implements IModule<HomeItem, DefaultViewHolder> {

    private final int[] itemViewTypes;
    private final Activity activity;
    private final boolean enableJump;

    public HomeItemModule(Activity activity, boolean enableJump, int... itemViewTypes) {
        this.itemViewTypes = itemViewTypes;
        this.activity = activity;
        this.enableJump = enableJump;
    }

    @NonNull
    @Override
    public IOptions<HomeItem, DefaultViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<HomeItem> dataGetter) {
        return configuration.options(this, IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY, dataGetter)
                .createItemView(R.layout.item_home_view, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder()
                            .setText(R.id.tv_text, data.getName())
                            .setImageResource(R.id.iv_icon, data.getIcon());
                }, itemViewTypes)
                .addClickItemViewListener(ClickAnimationProxy.zoom(0.8f, (options, viewHolderOwner) -> {
                    if (!enableJump) {
                        return;
                    }
                    HomeItem homeItem = options.getDataGetter().getData(viewHolderOwner.getContext().getAdapterPosition());
                    Intent intent = new Intent(activity, UniversalRecyclerViewActivity.class);
                    intent.putExtra(UniversalRecyclerViewActivity.TYPE_VIEW, homeItem.getType().getCanonicalName());
                    activity.startActivity(intent);
                }))
                .build();
    }
}
