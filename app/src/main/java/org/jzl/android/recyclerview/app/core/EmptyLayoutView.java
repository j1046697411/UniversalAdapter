package org.jzl.android.recyclerview.app.core;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.core.animation.Animation;
import org.jzl.android.recyclerview.app.core.animation.AnimationModule;
import org.jzl.android.recyclerview.core.*;
import org.jzl.android.recyclerview.core.layout.IEmptyLayoutManager;
import org.jzl.android.recyclerview.core.listeners.OnClickItemViewListener;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.plugins.AutomaticNotificationPlugin;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.ArrayList;
import java.util.Date;

public class EmptyLayoutView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    private final DataBlockProvider<Animation> dataBlockProvider;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public EmptyLayoutView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
        dataBlockProvider = DataBlockProviders.dataBlockProvider();
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        IConfiguration.<Animation, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataClassifier((configuration, data, position) -> 1)

                .setDataProvider(dataBlockProvider)
                .plugin(AutomaticNotificationPlugin.of())

                //实现空布局插件
                .plugin(IEmptyLayoutManager.of(IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE, null, new EmptyModule<>((options, viewHolderOwner) -> {
                    viewHolderOwner.getViewBinder()
                            .setText(R.id.tv_msg, R.string.loading)
                            .setVisibility(R.id.pb_progressBar, View.VISIBLE);
                    mainHandler.postDelayed(() -> {
                        add(dataBlockProvider);
                    }, 2000);
                }, IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE)))

                .registered(new AnimationModule(1))
                .build(recyclerView);

        IConfiguration.builder()
                //设置数据数据源
                .setDataProvider(DataBlockProviders.dataBlockProvider())
                //设置自动更新item插件
                .plugin(AutomaticNotificationPlugin.of())
                .build(recyclerView);

    }

    private void add(DataBlockProvider<Animation> dataBlockProvider) {
        ArrayList<Animation> animations = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Animation animation = new Animation(R.mipmap.animation_img1, "Hoteis in Rio de Janeiro", "" +
                    "He was one of Australia's most of distinguished artistes, renowned for his portraits", new Date());
            animations.add(animation);
        }
        dataBlockProvider.addAll(animations);
    }

    public static class EmptyModule<T> implements IModule<T, DefaultViewHolder> {

        private final OnClickItemViewListener<T, DefaultViewHolder> clickItemViewListener;
        private final int itemViewType;

        public EmptyModule(OnClickItemViewListener<T, DefaultViewHolder> clickItemViewListener, int itemViewType) {
            this.clickItemViewListener = clickItemViewListener;
            this.itemViewType = itemViewType;
        }

        @NonNull
        @Override
        public IOptions<T, DefaultViewHolder> setup(@NonNull  IConfiguration<?, ?> configuration, @NonNull IDataGetter<T> dataGetter) {
            return configuration.options(this, IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY, dataGetter)
                    .createItemView(R.layout.item_loading_view, itemViewType)
                    .addClickItemViewListener(clickItemViewListener, itemViewType)
                    .build();
        }
    }
}
