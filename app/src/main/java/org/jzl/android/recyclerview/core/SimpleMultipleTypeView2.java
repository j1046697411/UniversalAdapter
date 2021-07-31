package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.layout.SpanSize;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.model.Card;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Arrays;

public class SimpleMultipleTypeView2 extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public SimpleMultipleTypeView2(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {

        DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();

        dataBlockProvider.addAll(Arrays.asList(
                UniversalModel.build(
                        new Test1(R.mipmap.click_head_img_0, "不同数据类型的多类型列表", "不同数据类型的多类型列表")
                ).setItemViewType(1).setSpanSize(SpanSize.ALL).build(),
                UniversalModel.build(
                        new Test1(R.mipmap.click_head_img_0, "不同数据类型的多类型列表", "不同数据类型的多类型列表")
                ).setItemViewType(1).setSpanSize(SpanSize.ALL).build(),
                UniversalModel.build(
                        new Test1(R.mipmap.click_head_img_0, "不同数据类型的多类型列表", "不同数据类型的多类型列表")
                ).setItemViewType(1).setSpanSize(SpanSize.ALL).build()
        ));

        dataBlockProvider.addAll(Arrays.asList(
                UniversalModel.build(
                        new Test2(R.mipmap.click_head_img_0, "不同数据类型的多类型列表")
                ).setItemViewType(2).build(),
                UniversalModel.build(
                        new Test2(R.mipmap.click_head_img_0, "不同数据类型的多类型列表")
                ).setItemViewType(2).build(),
                UniversalModel.build(
                        new Test2(R.mipmap.click_head_img_0, "不同数据类型的多类型列表")
                ).setItemViewType(2).build(),
                UniversalModel.build(
                        new Test2(R.mipmap.click_head_img_0, "不同数据类型的多类型列表")
                ).setItemViewType(2).build(),
                UniversalModel.build(
                        new Test2(R.mipmap.click_head_img_0, "不同数据类型的多类型列表")
                ).setItemViewType(2).build(),
                UniversalModel.build(
                        new Test2(R.mipmap.click_head_img_0, "不同数据类型的多类型列表")
                ).setItemViewType(2).build()
        ));


        IConfiguration.<UniversalModel, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                .registered(new TestModule(), Functions.universal())
                .registered(new Test2Module(), Functions.universal())
                .build(recyclerView);
    }

    public static class TestModule implements IModule<Test1, DefaultViewHolder> {
        @NonNull
        @Override
        public IOptions<Test1, DefaultViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Test1> dataGetter) {
            return configuration.options(this, IViewHolderFactory.of(), dataGetter)
                    .createItemView(R.layout.item_click_view, 1)
                    .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                        context.getViewBinder()
                                .setText(R.id.tv_title, data.title)
                                .setImageResource(R.id.iv_icon, data.icon)
                                .setText(R.id.tv_subtitle, data.description);
                    }, 1)
                    .build();
        }
    }

    public static class Test2Module implements IModule<Test2, DefaultViewHolder> {

        @NonNull
        @Override
        public IOptions<Test2, DefaultViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Test2> dataGetter) {
            return configuration.options(this, IViewHolderFactory.of(), dataGetter)
                    .createItemView(R.layout.item_section_content, 2)
                    .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                        context.getViewBinder()
                                .setImageResource(R.id.iv_icon, data.icon)
                                .setText(R.id.tv_title, data.title);
                    }, 2)
                    .build();
        }
    }

    public static class Test1 {
        private final int icon;
        private final String title;
        private final String description;

        public Test1(int icon, String title, String description) {
            this.icon = icon;
            this.title = title;
            this.description = description;
        }
    }

    public static class Test2 {
        private final int icon;
        private final String title;

        public Test2(int icon, String title) {
            this.icon = icon;
            this.title = title;
        }
    }

}
