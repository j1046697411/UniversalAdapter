package org.jzl.android.recyclerview.app.core;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.core.animation.Animation;
import org.jzl.android.recyclerview.app.core.animation.AnimationModule;
import org.jzl.android.recyclerview.app.core.click.ItemClickModule;
import org.jzl.android.recyclerview.app.core.databinding.DataBindingModel;
import org.jzl.android.recyclerview.app.core.databinding.DataBindingModule;
import org.jzl.android.recyclerview.app.core.header.HeaderFooterModel;
import org.jzl.android.recyclerview.app.core.header.HeaderFooterModule;
import org.jzl.android.recyclerview.app.core.home.HomeItem;
import org.jzl.android.recyclerview.app.core.home.HomeItemModule;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.layout.IEmptyLayoutManager;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.layout.SpanSize;
import org.jzl.android.recyclerview.core.plugins.AutomaticNotificationPlugin;
import org.jzl.android.recyclerview.core.plugins.SelectPlugin;
import org.jzl.android.recyclerview.app.core.select.SelectModule;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.app.model.Card;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class MultipleTypeView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {
    public final Handler mainHandler = new Handler(Looper.getMainLooper());

    public MultipleTypeView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();

        IEmptyLayoutManager<UniversalModel, IViewHolder> layoutManager = IEmptyLayoutManager.of(IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE, null, new EmptyLayoutView.EmptyModule<>((options, viewHolderOwner) -> {
            viewHolderOwner.getViewBinder()
                    .setText(R.id.tv_msg, R.string.loading)
                    .setVisibility(R.id.pb_progressBar, View.VISIBLE);
            mainHandler.postDelayed(() -> {
                addUniversalModel(dataBlockProvider);
            }, 2000);
        }, IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE));

        SelectPlugin<UniversalModel, IViewHolder> selectPlugin = SelectPlugin.of(SelectPlugin.SelectMode.MULTIPLE, (context, viewHolder, data) -> {
            context.getViewBinder().setChecked(R.id.cb_select, data.isChecked());
        }, IMatchPolicy.ofItemTypes(7));

        IConfiguration.<UniversalModel, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .plugin(layoutManager)
                .plugin(selectPlugin)
                .plugin(AutomaticNotificationPlugin.of())
                .registered(new AnimationModule(1), Functions.universal())
                .registered(new HomeItemModule(parentView, false, 2), Functions.universal())
                .registered(new DataBindingModule(parentView, 3), Functions.universal())
                .registered(new HeaderFooterModule(parentView, 4), Functions.universal())
                .registered(new ItemClickModule(5, 6, parentView), Functions.universal())
                .registered(new SelectModule(parentView, 7), Functions.universal())
                .layoutManager(ILayoutManagerFactory.gridLayoutManager(2, GridLayoutManager.VERTICAL))
                .build(recyclerView);
    }

    public void addUniversalModel(Collection<UniversalModel> universalModels) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Animation animation = new Animation(R.mipmap.animation_img1, "Hoteis in Rio de Janeiro", "" +
                    "He was one of Australia's most of distinguished artistes, renowned for his portraits", new Date());
            universalModels.add(UniversalModel.build(animation).setItemViewType(1).setSpanSize(SpanSize.ALL).build());

            universalModels.add(UniversalModel.build(
                    new HomeItem("Universal", R.mipmap.click_head_img_0, null)
            ).setItemViewType(2).build());
            universalModels.add(UniversalModel.build(
                    new HomeItem("Universal", R.mipmap.click_head_img_1, null)
            ).setItemViewType(2).build());

            universalModels.add(UniversalModel.build(
                    new DataBindingModel(R.mipmap.databinding_img, "card", 0, "This card is exciting", random.nextInt(5) + 5)
            ).setItemViewType(3).setSpanSize(SpanSize.ALL).build());

            universalModels.add(UniversalModel.build(
                    new HeaderFooterModel("基础功能", "简单的类型")
            ).setSpanSize(SpanSize.ALL).setItemViewType(4).build());

            universalModels.add(UniversalModel.build(
                    new Card(R.mipmap.click_head_img_1, "card", "我是卡片")
            ).setSpanSize(SpanSize.ALL).setItemViewType(random.nextBoolean() ? 5 : 6).build());

            universalModels.add(UniversalModel.build(
                    new Card(R.mipmap.click_head_img_1, "card", "我是可选中的卡片")
            ).setSpanSize(SpanSize.ONE).setItemViewType(7).build());

            universalModels.add(UniversalModel.build(
                    new Card(R.mipmap.click_head_img_1, "card", "我是可选中的卡片")
            ).setSpanSize(SpanSize.ONE).setItemViewType(7).build());

        }

    }

}
