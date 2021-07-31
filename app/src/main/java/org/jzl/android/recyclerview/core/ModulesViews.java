package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.util.Functions;

import java.util.Arrays;

public class ModulesViews {

    public static class ModulesView1 extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

        public ModulesView1(@NonNull UniversalRecyclerViewActivity parentView) {
            super(parentView);
        }

        @Override
        public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
            IConfiguration.<String>builder()
                    .setDataProvider(Arrays.asList(
                            "代码复用的module模块功能",
                            "代码复用的module模块功能",
                            "代码复用的module模块功能",
                            "代码复用的module模块功能",
                            "代码复用的module模块功能",
                            "代码复用的module模块功能"
                    ))
                    .registered(new TestModule())
                    .setDataClassifier((configuration, data, position) -> 1)
                    .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                    .build(recyclerView);

        }
    }

    public static class ModulesView2 extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

        public ModulesView2(@NonNull UniversalRecyclerViewActivity parentView) {
            super(parentView);
        }

        @Override
        public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
            IConfiguration.<Test2>builder()
                    .setDataClassifier((configuration, data, position) -> position % 2 + 1)
                    .setDataProvider(Arrays.asList(
                            new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能"),
                            new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能"),
                            new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能"),
                            new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能"),
                            new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能"),
                            new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能")
                    ))
                    .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                    .registered(new Test2Module())
                    .registered(new TestModule(), target -> target.title)
                    .build(recyclerView);

        }
    }

    public static class ModulesView3 extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

        public ModulesView3(@NonNull UniversalRecyclerViewActivity parentView) {
            super(parentView);
        }

        @Override
        public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
            IConfiguration.<UniversalModel>builder()
                    .setDataProvider(Arrays.asList(
                            UniversalModel.build(
                                    new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能")
                            ).setItemViewType(2).build(),
                            UniversalModel.build(
                                    new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能")
                            ).setItemViewType(2).build(),
                            UniversalModel.build(
                                    new Test2(R.mipmap.click_head_img_0, "代码复用的module模块功能")
                            ).setItemViewType(2).build(),

                            UniversalModel.build("代码复用的module模块功能").setItemViewType(1).build(),
                            UniversalModel.build("代码复用的module模块功能").setItemViewType(1).build(),
                            UniversalModel.build("代码复用的module模块功能").setItemViewType(1).build()
                    ))
                    .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                    .registered(new Test2Module(), Functions.universal())
                    .registered(new TestModule(), Functions.universal())
                    .build(recyclerView);
        }
    }

    public static class TestModule implements IModule<String, DefaultViewHolder> {

        @NonNull
        @Override
        public IOptions<String, DefaultViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<String> dataGetter) {
            return configuration.options(this, IViewHolderFactory.of(), dataGetter)
                    .createItemView(R.layout.item_section_content, 1)
                    .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                        context.getViewBinder()
                                .setImageResource(R.id.iv_icon, R.mipmap.click_head_img_0)
                                .setText(R.id.tv_title, data);
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
                    })
                    .build();
        }
    }

    public static class Test2 {

        public final int icon;
        public final String title;

        public Test2(int icon, String title) {
            this.icon = icon;
            this.title = title;
        }
    }

}
