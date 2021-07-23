package org.jzl.android.recyclerview.core.listeners;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IViewHolder;

public interface IListenerManagerBuilder<T, VH extends IViewHolder, B extends IListenerManagerBuilder<T, VH, B>> {

    @NonNull
    default B addChildClickItemViewListener(IBindListener<VH, View.OnClickListener> bindListener, @NonNull OnClickItemViewListener<T, VH> clickItemViewListener, @NonNull IMatchPolicy matchPolicy) {
        return addCreatedViewHolderListener((options, viewHolderOwner) -> bindListener.bind(viewHolderOwner, v -> clickItemViewListener.onClickItemView(options, viewHolderOwner)), matchPolicy);
    }

    @NonNull
    default B addChildLongClickItemViewListener(IBindListener<VH, View.OnLongClickListener> bindListener, @NonNull OnLongClickItemViewListener<T, VH> longClickItemViewListener, IMatchPolicy matchPolicy) {
        return addCreatedViewHolderListener((options, viewHolderOwner) -> bindListener.bind(viewHolderOwner, v -> longClickItemViewListener.onLongClickItemView(options, viewHolderOwner)), matchPolicy);
    }

    @NonNull
    B addCreatedViewHolderListener(@NonNull OnCreatedViewHolderListener<T, VH> createdViewHolderListener, @NonNull IMatchPolicy matchPolicy);

    @NonNull
    default B addCreatedViewHolderListener(@NonNull OnCreatedViewHolderListener<T, VH> createdViewHolderListener, int... itemViewTypes) {
        return addCreatedViewHolderListener(createdViewHolderListener, IMatchPolicy.ofItemTypes(itemViewTypes));
    }

    @NonNull
    default B addClickItemViewListener(@NonNull OnClickItemViewListener<T, VH> clickItemViewListener, @NonNull IMatchPolicy bindPolicy) {
        return addCreatedViewHolderListener((options, viewHolderOwner) -> {
            viewHolderOwner.getViewBinder().addClickListener(viewHolderOwner.getItemView(), v -> {
                    clickItemViewListener.onClickItemView(options, viewHolderOwner);
            });
        }, bindPolicy);
    }

    @NonNull
    default B addClickItemViewListener(@NonNull OnClickItemViewListener<T, VH> clickItemViewListener, @NonNull int... itemViewTypes) {
        return addClickItemViewListener(clickItemViewListener, IMatchPolicy.ofItemTypes(itemViewTypes));
    }

    @NonNull
    default B addLongClickItemViewListener(@NonNull OnLongClickItemViewListener<T, VH> longClickItemViewListener, @NonNull IMatchPolicy matchPolicy) {
        return addCreatedViewHolderListener((options, viewHolderOwner) -> {
            viewHolderOwner.getViewBinder().addLongClickListener(viewHolderOwner.getItemView(), v -> longClickItemViewListener.onLongClickItemView(options, viewHolderOwner));
        }, matchPolicy);
    }

    @NonNull
    default B addLongClickItemViewListener(@NonNull OnLongClickItemViewListener<T, VH> longClickItemViewListener, int... itemViewTypes) {
        return addLongClickItemViewListener(longClickItemViewListener, IMatchPolicy.ofItemTypes(itemViewTypes));
    }

    @NonNull
    B addViewAttachedToWindowListener(@NonNull OnViewAttachedToWindowListener<T, VH> viewAttachedToWindowListener, @NonNull IBindPolicy bindPolicy);

    @NonNull
    B addViewDetachedFromWindowListener(@NonNull OnViewDetachedFromWindowListener<T, VH> viewDetachedFromWindowListener, @NonNull IBindPolicy bindPolicy);

    @NonNull
    B addAttachedToRecyclerViewListener(@NonNull OnAttachedToRecyclerViewListener<T, VH> attachedToRecyclerViewListener);

    @NonNull
    B addDetachedFromRecyclerViewListener(@NonNull OnDetachedFromRecyclerViewListener<T, VH> detachedFromRecyclerViewListener);

    B addViewRecycledListener(@NonNull OnViewRecycledListener<T, VH> viewRecycledListener, @NonNull IBindPolicy bindPolicy);

}
