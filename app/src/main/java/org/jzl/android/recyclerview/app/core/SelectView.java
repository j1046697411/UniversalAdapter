package org.jzl.android.recyclerview.app.core;

import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.layout.SpanSize;
import org.jzl.android.recyclerview.core.plugins.SelectPlugin;
import org.jzl.android.recyclerview.app.core.select.SelectModule;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.app.model.Card;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SelectView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    SelectPlugin<UniversalModel, IViewHolder> selectPlugin;

    public SelectView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        menu(activityRevyclerViewBinding);
        selectPlugin = SelectPlugin.of(SelectPlugin.SelectMode.MULTIPLE, (context, viewHolder, data) -> {
            context.getViewBinder().setChecked(R.id.cb_select, data.isChecked());
        }, IMatchPolicy.ofItemTypes(1));

        DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);

        IConfiguration.<UniversalModel, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .plugin(selectPlugin)
                .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                .setDataProvider(dataBlockProvider)
                .registered(new SelectModule(parentView, 1), Functions.universal())
                .build(recyclerView);
    }

    private void menu(ActivityRevyclerViewBinding activityRevyclerViewBinding) {
        activityRevyclerViewBinding.llHeader.setVisibility(View.VISIBLE);
        activityRevyclerViewBinding.msSpinner.setItems("全选", "取消", "反选", "获取结果");
        activityRevyclerViewBinding.tvHeaderMsg.setText("SelectMode");
        activityRevyclerViewBinding.switchButton.setText( SelectPlugin.SelectMode.SINGLE.name(), SelectPlugin.SelectMode.MULTIPLE.name());
        activityRevyclerViewBinding.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                selectPlugin.setSelectMode(SelectPlugin.SelectMode.SINGLE);
            }else{
                selectPlugin.setSelectMode(SelectPlugin.SelectMode.MULTIPLE);
            }
            Toast.makeText(parentView.getApplication(), "切换:" + selectPlugin.getSelectMode(), Toast.LENGTH_SHORT).show();
        });

        activityRevyclerViewBinding.msSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            if (position == 0) {
                selectPlugin.checkedAll();
            } else if (position == 1) {
                selectPlugin.uncheckedAll();
            } else if (position == 2) {
                selectPlugin.reverseAll();
            } else {
                List<UniversalModel> universalModels = selectPlugin.getSelectResult();
                Toast.makeText(parentView.getApplication(), "选中: " + universalModels.size() + "个", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add(Collection<UniversalModel> models) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            models.add(UniversalModel.build(
                    new Card(random.nextBoolean() ? R.mipmap.click_head_img_0 : R.mipmap.click_head_img_1, "card " + (i + 1), "简单可以点击的卡片")
            ).setSpanSize(SpanSize.ONE).setItemViewType(1).build());
        }
    }

}
