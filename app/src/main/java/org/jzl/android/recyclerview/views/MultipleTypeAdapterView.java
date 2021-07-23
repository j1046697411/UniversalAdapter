package org.jzl.android.recyclerview.views;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.model.Book;
import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.android.recyclerview.modules.ClickBookModule;
import org.jzl.android.recyclerview.util.BookUtils;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;
import org.jzl.android.recyclerview.vm.BookModule;

public class MultipleTypeAdapterView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public MultipleTypeAdapterView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        BookUtils.randomFilling(dataBlockProvider, 50);
        IConfiguration.<UniversalModel, IViewHolder>builder((options, itemView, itemViewType) -> new DefaultViewHolder(itemView, itemViewType))
                .setDataClassifier((configuration, data, position) -> position % 2 == 0 ? BookModule.ITEM_VIEW_TYPE_BOOK : ClickBookModule.ITEM_VIEW_TYPE_CLICK_BOOK)
                .setDataProvider(dataBlockProvider)
                .registered(new ClickBookModule(this), Functions.universal())
                .registered(new BookModule(parentView), Functions.universal())
                .build(recyclerView);
    }
}
