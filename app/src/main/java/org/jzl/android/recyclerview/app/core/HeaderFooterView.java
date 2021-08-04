package org.jzl.android.recyclerview.app.core;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.core.header.HeaderFooterModel;
import org.jzl.android.recyclerview.app.core.header.HeaderFooterModule;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.layout.SpanSize;
import org.jzl.android.recyclerview.core.plugins.AutomaticNotificationPlugin;
import org.jzl.android.recyclerview.core.plugins.SelectPlugin;
import org.jzl.android.recyclerview.app.core.select.SelectModule;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.app.model.Card;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.android.recyclerview.util.datablock.DataBlock;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;
import org.jzl.android.recyclerview.util.datablock.PositionType;
import org.jzl.lang.util.CollectionUtils;

public class HeaderFooterView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    private final DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();

    private final DataBlock<UniversalModel> header = dataBlockProvider.dataBlock(PositionType.HEADER, 1);
    private final DataBlock<UniversalModel> footer = dataBlockProvider.dataBlock(PositionType.FOOTER, 2);
    private final DataBlock<UniversalModel> content = dataBlockProvider.lastContentDataBlock();

    public HeaderFooterView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        menu(activityRevyclerViewBinding);
        add();
        IConfiguration.<UniversalModel, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .plugin(AutomaticNotificationPlugin.of())

                .plugin(SelectPlugin.of(SelectPlugin.SelectMode.SINGLE, (context, viewHolder, data) -> {
                    context.getViewBinder().setChecked(R.id.cb_select, data.isChecked());
                }, IMatchPolicy.ofItemTypes(2)))

                .registered(new HeaderFooterModule(parentView, 1), Functions.universal())
                .registered(new SelectModule(parentView, 2), Functions.universal())
                .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                .build(recyclerView);

    }

    private void add() {
        header.add(UniversalModel.build(
                new HeaderFooterModel("我是header", "简单的带数据的header")
        ).setItemViewType(1).setSpanSize(SpanSize.ALL).build());

        footer.add(UniversalModel.build(
                new HeaderFooterModel("我是footer", "简单的带数据的footer")
        ).setItemViewType(1).setSpanSize(SpanSize.ALL).build());

        for (int i = 0; i < 10; i++) {
            content.add(UniversalModel.build(
                    new Card(R.mipmap.click_head_img_0, "我是内容", "我是内容")
            ).setItemViewType(2).build());
        }
    }

    private void menu(ActivityRevyclerViewBinding activityRevyclerViewBinding) {
        activityRevyclerViewBinding.llHeader.setVisibility(View.VISIBLE);

        activityRevyclerViewBinding.msSpinner.setItems(
                "add header",
                "remove header",
                "remove all header",
                "add footer",
                "remove footer",
                "remove all footer"
        );
        activityRevyclerViewBinding.msSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            switch (position) {
                case 0: {
                    header.add(UniversalModel.build(
                            new HeaderFooterModel("我是header", "简单的带数据的header")
                    ).setItemViewType(1).setSpanSize(SpanSize.ALL).build());
                    break;
                }
                case 1: {
                    if (CollectionUtils.nonEmpty(header)) {
                        header.remove(0);
                        break;
                    }
                }
                case 2: {
                    header.clear();
                    break;
                }
                case 3: {
                    footer.add(UniversalModel.build(
                            new HeaderFooterModel("我是footer", "简单的带数据的footer")
                    ).setItemViewType(1).setSpanSize(SpanSize.ALL).build());
                    break;
                }

                case 4: {
                    if (CollectionUtils.nonEmpty(footer)) {
                        footer.remove(0);
                        break;
                    }
                }
                case 5: {
                    footer.clear();
                    break;
                }
            }
        });
    }
}
