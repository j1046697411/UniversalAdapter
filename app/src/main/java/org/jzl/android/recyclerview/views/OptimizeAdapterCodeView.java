package org.jzl.android.recyclerview.views;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.model.Book;
import org.jzl.android.recyclerview.modules.BookBinder;
import org.jzl.android.recyclerview.util.BookUtils;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

public class OptimizeAdapterCodeView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public OptimizeAdapterCodeView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<Book> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        BookUtils.randomFillingBooks(dataBlockProvider, 50);
        IConfiguration.<Book>builder()
                .createItemView(R.layout.layout_book)
                .setDataProvider(dataBlockProvider)
                .dataBindingByItemViewTypes(new BookBinder())
                .build(recyclerView);
    }
}
