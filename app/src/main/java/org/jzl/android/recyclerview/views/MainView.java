package org.jzl.android.recyclerview.views;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.listeners.IBindListener;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;

import java.util.Arrays;
import java.util.List;

public class MainView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    private final List<Class<?>> classes = Arrays.asList(
            ItemClickListenerView.class,
            MultipleTypeAdapterView.class,
            OptimizeAdapterCodeView.class
    );

    public MainView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        IConfiguration.<Class<?>>builder()
                .setDataProvider(classes)
                .createItemView(R.layout.item_button)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder().setText(R.id.btn_item, data.getSimpleName());
                })
                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.btn_item), (options, viewHolderOwner) -> {
                    Class<?> type = options.getDataGetter().getData(viewHolderOwner.getContext().getAdapterPosition());
                    Intent intent = new Intent(parentView, UniversalRecyclerViewActivity.class);
                    intent.putExtra(UniversalRecyclerViewActivity.TYPE_VIEW, type.getCanonicalName());
                    parentView.startActivity(intent);
                }, IBindPolicy.BIND_POLICY_ALL)
                .build(recyclerView);
    }
}
