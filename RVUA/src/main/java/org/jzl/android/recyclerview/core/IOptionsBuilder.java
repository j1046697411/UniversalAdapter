package org.jzl.android.recyclerview.core;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.listeners.IListenerManagerBuilder;
import org.jzl.lang.fun.Function;

public interface IOptionsBuilder<T, VH extends IViewHolder> extends IListenerManagerBuilder<T, VH, IOptionsBuilder<T, VH>> {

    @NonNull
    IOptions<T, VH> build();

    @NonNull
    IOptionsBuilder<T, VH> setViewFactoryStoreFactory(IViewFactoryStoreFactory<T, VH> viewFactoryStoreFactory);

    @NonNull
    default IOptionsBuilder<T, VH> createItemView(@LayoutRes int layoutResId, int... itemViewTypes) {
        return createItemView(IViewFactory.of(layoutResId), itemViewTypes);
    }

    @NonNull
    default IOptionsBuilder<T, VH> createItemView(@NonNull IViewFactory viewFactory, int... itemViewTypes) {
        return createItemView(viewFactory, IMatchPolicy.ofItemTypes(itemViewTypes), 10);
    }

    @NonNull
    default IOptionsBuilder<T, VH> createItemView(@NonNull IViewFactory viewFactory, @NonNull IMatchPolicy matchPolicy, int priority) {
        return createItemView(options -> new ViewFactoryOwner<>(options, viewFactory, matchPolicy, priority));
    }

    @NonNull
    default IOptionsBuilder<T, VH> createItemView(IViewFactoryOwner<VH> viewFactoryOwner) {
        return createItemView(options -> viewFactoryOwner);
    }

    @NonNull
    IOptionsBuilder<T, VH> createItemView(@NonNull Function<IOptions<T, VH>, IViewFactoryOwner<VH>> consumer);

    @NonNull
    IOptionsBuilder<T, VH> setDataBinderStoreFactory(IDataBinderStoreFactory<T, VH> dataBinderStoreFactory);

    @NonNull
    IOptionsBuilder<T, VH> dataBinding(@NonNull IDataBinder<T, VH> dataBinder, @NonNull IBindPolicy bindPolicy, int priority);

    @NonNull
    default IOptionsBuilder<T, VH> dataBinding(IDataBinder<T, VH> dataBinder, @NonNull IBindPolicy bindPolicy) {
        return dataBinding(dataBinder, bindPolicy, 10);
    }

    @NonNull
    default IOptionsBuilder<T, VH> dataBindingByItemViewTypes(@NonNull IDataBinder<T, VH> dataBinder, int... itemViewTypes) {
        return dataBinding(dataBinder, IBindPolicy.ofItemViewTypes(itemViewTypes));
    }

    @NonNull
    default IOptionsBuilder<T, VH> dataBindingByPayloads(@NonNull IDataBinder<T, VH> dataBinder, Object... payloads) {
        return dataBinding(dataBinder, IBindPolicy.ofPayloads(payloads));
    }

    default IOptionsBuilder<T, VH> dataBindingByPayloadsOrNotIncludedPayloads(@NonNull IDataBinder<T, VH> dataBinder, Object... payloads) {
        return dataBinding(dataBinder, IBindPolicy.ofPayloadsOrNotIncludedPayload(payloads));
    }

    @NonNull
    IOptionsBuilder<T, VH> setPriority(int priority);

}
