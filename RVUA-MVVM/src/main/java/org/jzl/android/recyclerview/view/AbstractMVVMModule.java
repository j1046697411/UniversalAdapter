package org.jzl.android.recyclerview.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IViewModel;
import org.jzl.android.mvvm.view.helper.IViewHelperCallback;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IOptionsBuilder;
import org.jzl.android.recyclerview.core.IViewHolderFactory;
import org.jzl.android.recyclerview.core.module.IModule;

public abstract class AbstractMVVMModule<T, VM extends IViewModel<ViewHolderHelper<VM, VDB>>, VDB extends ViewDataBinding> implements
        IModule<T, ViewHolderHelper<VM, VDB>>, IViewHelperCallback<ViewHolderHelper<VM, VDB>, VM, VDB>, IViewHolderFactory<ViewHolderHelper<VM, VDB>> {

    private final IExtendView<?, ?, ?> parentContainerView;

    public AbstractMVVMModule(IExtendView<?, ?, ?> parentContainerView) {
        this.parentContainerView = parentContainerView;
    }

    @NonNull
    @Override
    public final IOptions<T, ViewHolderHelper<VM, VDB>> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<T> dataGetter) {
        return setup(configuration.options(this, this, dataGetter));
    }

    @NonNull
    @Override
    public final ViewHolderHelper<VM, VDB> createViewHolder(@NonNull IOptions<?, ViewHolderHelper<VM, VDB>> options, @NonNull View itemView, int itemViewType) {
        return new ViewHolderHelper<>(parentContainerView, options, itemView, getViewModelType(itemViewType), getVariableId(itemViewType), itemViewType, this);
    }

    @NonNull
    protected abstract IOptions<T, ViewHolderHelper<VM, VDB>> setup(IOptionsBuilder<T, ViewHolderHelper<VM, VDB>> optionsBuilder);

    protected abstract int getVariableId(int itemViewType);

    @NonNull
    protected abstract Class<VM> getViewModelType(int itemViewType);

}
