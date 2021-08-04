package org.jzl.android.recyclerview.app.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.app.model.Card;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Arrays;

public class SimpleMultipleTypeView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public SimpleMultipleTypeView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {

        DataBlockProvider<Card> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        dataBlockProvider.addAll(Arrays.asList(
                new Card(R.mipmap.click_head_img_0, "card ", "多类型卡片"),
                new Card(R.mipmap.click_head_img_0, "card ", "多类型卡片"),
                new Card(R.mipmap.click_head_img_0, "card ", "多类型卡片"),
                new Card(R.mipmap.click_head_img_0, "card ", "多类型卡片"),
                new Card(R.mipmap.click_head_img_0, "card ", "多类型卡片")
        ));

        IConfiguration.<Card>builder()
                .setDataProvider(dataBlockProvider)
                .setDataClassifier((configuration, data, position) -> {
                    return position % 2 + 1;
                })
                .createItemView(R.layout.item_click_view, 1)
                .createItemView(R.layout.item_click_childview, 2)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder()
                            .setText(R.id.tv_title, data.getTitle())
                            .setImageResource(R.id.iv_icon, data.getIcon())
                            .setText(R.id.tv_subtitle, data.getDescription());
                }, 1, 2)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder().setText(R.id.tv_num, String.valueOf(data.getNumber()));
                }, 2)
                .build(recyclerView);
    }
}
