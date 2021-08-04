package org.jzl.android.recyclerview.app.core.animation;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;

import java.text.SimpleDateFormat;

public class AnimationModule implements IModule<Animation, DefaultViewHolder> {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    private final int[] itemViewTypes;

    public AnimationModule(int... itemViewTypes) {
        this.itemViewTypes = itemViewTypes;
    }

    @NonNull
    @Override
    public IOptions<Animation, DefaultViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Animation> dataGetter) {
        return configuration.options(this, IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY, dataGetter)
                .createItemView(R.layout.item_animation, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder()
                            .setImageResource(R.id.iv_icon, data.getIcon())
                            .setText(R.id.tv_title, data.getTitle())
                            .setText(R.id.tv_refresh_time, SIMPLE_DATE_FORMAT.format(data.getTime()))
                            .setText(R.id.tv_subtitle, data.getDescription());
                }, itemViewTypes)
                .build();
    }
}
