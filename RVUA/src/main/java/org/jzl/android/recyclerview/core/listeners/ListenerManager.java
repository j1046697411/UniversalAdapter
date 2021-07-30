package org.jzl.android.recyclerview.core.listeners;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;
import org.jzl.lang.util.ForeachUtils;
import org.jzl.lang.util.holder.BinaryHolder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ListenerManager<T, VH extends IViewHolder> implements IListenerManager<T, VH> {

    private final List<BinaryHolder<IMatchPolicy, OnCreatedViewHolderListener<T, VH>>> createdViewHolderListeners = new CopyOnWriteArrayList<>();
    private final List<BinaryHolder<IBindPolicy, OnViewAttachedToWindowListener<T, VH>>> viewAttachedToWindowListeners = new CopyOnWriteArrayList<>();
    private final List<BinaryHolder<IBindPolicy, OnViewDetachedFromWindowListener<T, VH>>> viewDetachedFromWindowListeners = new CopyOnWriteArrayList<>();
    private final List<BinaryHolder<IBindPolicy, OnViewRecycledListener<T, VH>>> viewRecycledListeners = new CopyOnWriteArrayList<>();

    private final List<OnAttachedToRecyclerViewListener<T, VH>> attachedToRecyclerViewListeners = new CopyOnWriteArrayList<>();
    private final List<OnDetachedFromRecyclerViewListener<T, VH>> detachedFromRecyclerViewListeners = new CopyOnWriteArrayList<>();

    @Override
    public void notifyCreatedViewHolder(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner, int viewType) {
        ForeachUtils.each(createdViewHolderListeners, target -> {
            if (target.one.match(viewType)) {
                target.two.onCreatedViewHolder(options, viewHolderOwner);
            }
        });
    }

    @Override
    public void notifyViewAttachedToWindow(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner) {
        ForeachUtils.each(this.viewAttachedToWindowListeners, target -> {
            if (target.one.match(viewHolderOwner.getContext())) {
                target.two.onViewAttachedToWindow(options, viewHolderOwner);
            }
        });
    }

    @Override
    public void notifyViewDetachedFromWindow(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner) {
        ForeachUtils.each(this.viewDetachedFromWindowListeners, target -> {
            if (target.one.match(viewHolderOwner.getContext())) {
                target.two.onViewDetachedFromWindow(options, viewHolderOwner);
            }
        });
    }

    @Override
    public void notifyAttachedToRecyclerView(@NonNull RecyclerView recyclerView, @NonNull IOptions<T, VH> options) {
        ForeachUtils.each(this.attachedToRecyclerViewListeners, target -> target.onAttachedToRecyclerView(recyclerView, options));
    }

    @Override
    public void notifyDetachedFromRecyclerView(@NonNull RecyclerView recyclerView, @NonNull IOptions<T, VH> options) {
        ForeachUtils.each(this.detachedFromRecyclerViewListeners, target -> target.onDetachedFromRecyclerView(recyclerView, options));
    }

    @Override
    public void notifyViewRecycled(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner) {
        ForeachUtils.each(this.viewRecycledListeners, target -> {
            if (target.one.match(viewHolderOwner.getContext())) {
                target.two.onViewRecycled(options, viewHolderOwner);
            }
        });
    }

    @NonNull
    @Override
    public IListenerManager<T, VH> addViewAttachedToWindowListener(@NonNull OnViewAttachedToWindowListener<T, VH> viewAttachedToWindowListener, @NonNull IBindPolicy bindPolicy) {
        this.viewAttachedToWindowListeners.add(BinaryHolder.of(bindPolicy, viewAttachedToWindowListener));
        return this;
    }

    @NonNull
    @Override
    public IListenerManager<T, VH> addViewDetachedFromWindowListener(@NonNull OnViewDetachedFromWindowListener<T, VH> viewDetachedFromWindowListener, @NonNull IBindPolicy bindPolicy) {
        this.viewDetachedFromWindowListeners.add(BinaryHolder.of(bindPolicy, viewDetachedFromWindowListener));
        return this;
    }

    @NonNull
    @Override
    public IListenerManager<T, VH> addAttachedToRecyclerViewListener(@NonNull OnAttachedToRecyclerViewListener<T, VH> attachedToRecyclerViewListener) {
        this.attachedToRecyclerViewListeners.add(attachedToRecyclerViewListener);
        return this;
    }

    @NonNull
    @Override
    public IListenerManager<T, VH> addDetachedFromRecyclerViewListener(@NonNull OnDetachedFromRecyclerViewListener<T, VH> detachedFromRecyclerViewListener) {
        this.detachedFromRecyclerViewListeners.add(detachedFromRecyclerViewListener);
        return this;
    }

    @Override
    public IListenerManager<T, VH> addViewRecycledListener(@NonNull OnViewRecycledListener<T, VH> viewRecycledListener, @NonNull IBindPolicy bindPolicy) {
        this.viewRecycledListeners.add(BinaryHolder.of(bindPolicy, viewRecycledListener));
        return this;
    }

    @NonNull
    @Override
    public IListenerManager<T, VH> addCreatedViewHolderListener(@NonNull OnCreatedViewHolderListener<T, VH> createdViewHolderListener, @NonNull IMatchPolicy matchPolicy) {
        this.createdViewHolderListeners.add(BinaryHolder.of(matchPolicy, createdViewHolderListener));
        return this;
    }

}
