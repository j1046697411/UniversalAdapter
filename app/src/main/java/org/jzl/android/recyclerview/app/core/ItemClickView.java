package org.jzl.android.recyclerview.app.core;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.app.core.click.ItemClickModule;
import org.jzl.android.recyclerview.app.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.app.model.Card;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.Collection;
import java.util.Random;

public class ItemClickView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public ItemClickView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<Card> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);
        IConfiguration.<Card>builder()
                .setDataProvider(dataBlockProvider)
                .setDataClassifier((configuration, data, position) -> {
                    if (data.getIcon() == R.mipmap.click_head_img_0) {
                        return 1;
                    } else {
                        return 2;
                    }
                })
                .registered(new ItemClickModule(1, 2, parentView))
                .build(recyclerView);
    }

    private void add(Collection<Card> cards) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            cards.add(new Card(random.nextBoolean() ? R.mipmap.click_head_img_0 : R.mipmap.click_head_img_1, "card " + (i + 1), "简单可以点击的卡片"));
        }

    }

}
