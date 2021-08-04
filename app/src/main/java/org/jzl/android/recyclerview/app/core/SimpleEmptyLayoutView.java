package org.jzl.android.recyclerview.app.core;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.layout.IEmptyLayoutManager;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.plugins.AutomaticNotificationPlugin;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

public class SimpleEmptyLayoutView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public SimpleEmptyLayoutView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<String> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        IConfiguration.<String, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .setDataClassifier((configuration, data, position) -> 1)

                //自动通知跟新Item
                .plugin(AutomaticNotificationPlugin.of())

                //空布局插件
                .plugin(IEmptyLayoutManager.of(R.layout.item_loading_view))

                .registered(new ModulesViews.TestModule())

                //空布局点击事件监听
                .addClickItemViewListener((options, viewHolderOwner) -> {
                    viewHolderOwner.getViewBinder()
                            .setVisibility(R.id.pb_progressBar, View.VISIBLE)
                            .setText(R.id.tv_msg, R.string.loading);
                    mainHandler.postDelayed(() -> add(dataBlockProvider), 2000);
                }, IMatchPolicy.ofItemTypes(IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE))

                .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))

                .build(recyclerView);
    }

    private void add(DataBlockProvider<String> dataBlockProvider) {
        for (int i = 0; i < 100; i++) {
            dataBlockProvider.add("空布局");
        }
    }

}
