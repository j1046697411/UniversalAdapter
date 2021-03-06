package org.jzl.android.recyclerview.app.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.core.header.HeaderFooterModel;
import org.jzl.android.recyclerview.app.core.header.HeaderFooterModule;
import org.jzl.android.recyclerview.app.core.home.HomeItem;
import org.jzl.android.recyclerview.app.core.home.HomeItemModule;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.layout.SpanSize;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;

public class HomeView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public HomeView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        addHomes(dataBlockProvider);
        IConfiguration.<UniversalModel, IViewHolder>builder((options, itemView, itemViewType) -> new DefaultViewHolder(itemView, itemViewType))
                .setDataProvider(dataBlockProvider)
                .layoutManager(ILayoutManagerFactory.gridLayoutManager(2, GridLayoutManager.VERTICAL))
                .registered(new HeaderFooterModule(parentView, 1), Functions.universal())
                .registered(new HomeItemModule(parentView, true, 2), Functions.universal())
                .build(recyclerView);
    }

    public static void addHomes(Collection<UniversalModel> universalModels) {
        universalModels.add(UniversalModel.build(new HeaderFooterModel("UniversalAdapter????????????", ""))
                .setSpanSize(SpanSize.ALL)
                .setItemViewType(1)
                .build());
        universalModels.add(UniversalModel.build(new HomeItem("Optimize Code", R.mipmap.gv_empty, OptimizeCodeView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("Empty Layout", R.mipmap.gv_empty, EmptyLayoutView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("animation", R.mipmap.gv_animation, AnimationView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("multiple item", R.mipmap.gv_multiple_item, MultipleTypeView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("item click", R.mipmap.gv_item_click, ItemClickView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("section", R.mipmap.gv_section, SelectView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("header/footer", R.mipmap.gv_header_and_footer, HeaderFooterView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("data binding", R.mipmap.gv_databinding, DataBindingView.class)).setItemViewType(2).build());

        universalModels.add(UniversalModel.build(new HeaderFooterModel("UniversalAdapter????????????", ""))
                .setSpanSize(SpanSize.ALL)
                .setItemViewType(1)
                .build());
        universalModels.add(UniversalModel.build(new HomeItem("??????????????????", R.mipmap.gv_multiple_item, SimpleMultipleTypeView.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("????????????????????????????????????", R.mipmap.gv_multiple_item, SimpleMultipleTypeView2.class)).setItemViewType(2).build());

        universalModels.add(UniversalModel.build(new HomeItem("???????????????module????????????1", R.mipmap.gv_multiple_item, ModulesViews.ModulesView1.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("???????????????module????????????2", R.mipmap.gv_multiple_item, ModulesViews.ModulesView2.class)).setItemViewType(2).build());
        universalModels.add(UniversalModel.build(new HomeItem("???????????????module????????????3", R.mipmap.gv_multiple_item, ModulesViews.ModulesView3.class)).setItemViewType(2).build());

        universalModels.add(UniversalModel.build(new HomeItem("DataBinding", R.mipmap.gv_multiple_item, DataBindingView2.class)).setItemViewType(2).build());

        universalModels.add(UniversalModel.build(new HomeItem("??????????????????", R.mipmap.gv_multiple_item, SimpleEmptyLayoutView.class)).setItemViewType(2).build());

    }

}
