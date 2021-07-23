package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.util.DateUtils;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;
import java.util.Date;

public class OptimizeCodeView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public OptimizeCodeView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<Object> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);

        IConfiguration.builder()
                .setDataProvider(dataBlockProvider)
                .createItemView(R.layout.item_animation)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder()
                            .setImageResource(R.id.iv_icon, R.mipmap.animation_img1)
                            .setText(R.id.tv_title, "OptimizeCode")
                            .setText(R.id.tv_refresh_time, DateUtils.format(new Date()))
                            .setText(R.id.tv_subtitle, "最简单的操作，实现最复杂的处理");
                })
                .build(recyclerView);

    }

    public void add(Collection<?> collection) {
        for (int i = 0; i < 100; i++) {
            collection.add(null);
        }
    }

}
