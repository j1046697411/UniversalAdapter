package org.jzl.android.recyclerview.core.click;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.listeners.IBindListener;
import org.jzl.android.recyclerview.core.listeners.proxy.ClickAnimationProxy;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.model.Card;

public class ItemClickModule implements IModule<Card, DefaultViewHolder> {

    public static final String payload_update_num = "payload_update_num";
    public final int itemViewType1;
    private final int itemViewType2;
    private final Activity activity;

    public ItemClickModule(int itemViewType1, int itemViewType2, Activity activity) {
        this.itemViewType1 = itemViewType1;
        this.itemViewType2 = itemViewType2;
        this.activity = activity;
    }

    @NonNull
    @Override
    public IOptions<Card, DefaultViewHolder> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<Card> dataGetter) {
        return configuration.options(this, IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY, dataGetter)
                .createItemView(R.layout.item_click_view, itemViewType1)
                .createItemView(R.layout.item_click_childview, itemViewType2)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    context.getViewBinder()
                            .setText(R.id.tv_title, data.getTitle())
                            .setImageResource(R.id.iv_icon, data.getIcon())
                            .setText(R.id.tv_subtitle, data.getDescription());
                }, itemViewType1, itemViewType2)
                .dataBinding((context, viewHolder, data) -> {
                    context.getViewBinder().setText(R.id.tv_num, String.valueOf(data.getNumber()));
                }, IBindPolicy.ofItemViewTypes(itemViewType2).and(IBindPolicy.ofPayloadsOrNotIncludedPayload(payload_update_num)))

                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.btn_click_me), (options, viewHolderOwner) -> {
                    Toast.makeText(activity, "点击我", Toast.LENGTH_SHORT).show();
                }, IMatchPolicy.ofItemTypes(itemViewType1))
                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.iv_num_add), (options, viewHolderOwner) -> {
                    int index = viewHolderOwner.getContext().getAdapterPosition();
                    Card card = options.getDataGetter().getData(index);
                    card.setNumber(card.getNumber() + 1);
                    options.getConfiguration().getAdapterObservable().notifyItemRangeChanged(index, 1, payload_update_num);
                }, IMatchPolicy.ofItemTypes(itemViewType2))
                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.iv_num_reduce), (options, viewHolderOwner) -> {
                    int index = viewHolderOwner.getContext().getAdapterPosition();
                    Card card = options.getDataGetter().getData(index);
                    card.setNumber(card.getNumber() - 1);
                    options.getConfiguration().getAdapterObservable().notifyItemRangeChanged(index, 1, payload_update_num);
                }, IMatchPolicy.ofItemTypes(itemViewType2))

                .addClickItemViewListener(ClickAnimationProxy.zoom(0.8f, (options, viewHolderOwner) -> {
                    Toast.makeText(activity, "点击了item", Toast.LENGTH_SHORT).show();
                }), IMatchPolicy.MATCH_POLICY_ALL)
                .build();
    }
}
