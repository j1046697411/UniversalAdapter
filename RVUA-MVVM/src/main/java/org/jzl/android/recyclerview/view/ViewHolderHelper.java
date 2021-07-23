package org.jzl.android.recyclerview.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IViewBindingHelper;
import org.jzl.android.mvvm.IViewBindingHelperFactory;
import org.jzl.android.mvvm.IViewModel;
import org.jzl.android.mvvm.view.helper.IViewHelperCallback;
import org.jzl.android.recyclerview.core.IOptions;

public class ViewHolderHelper<VM extends IViewModel<ViewHolderHelper<VM, VDB>>, VDB extends ViewDataBinding> extends AbstractMVVMViewHolder<ViewHolderHelper<VM, VDB>, VM, VDB> {

    private final IOptions<?, ViewHolderHelper<VM, VDB>> options;
    private final Class<VM> viewModelType;
    private final int variableId;
    private final IViewHelperCallback<ViewHolderHelper<VM, VDB>, VM, VDB> callback;
    private final int itemViewType;

    ViewHolderHelper(@NonNull IExtendView<?, ?, ?> parentContainerView, @NonNull IOptions<?, ViewHolderHelper<VM, VDB>> options, @NonNull View itemView, @NonNull Class<VM> viewModelType, int variableId, int itemViewType, @NonNull IViewHelperCallback<ViewHolderHelper<VM, VDB>, VM, VDB> callback) {
        super(parentContainerView, itemView);
        this.viewModelType = viewModelType;
        this.options = options;
        this.variableId = variableId;
        this.callback = callback;
        this.itemViewType = itemViewType;
    }

    @NonNull
    @Override
    public IViewBindingHelper<ViewHolderHelper<VM, VDB>, VM> createViewBindingHelper(IViewBindingHelperFactory factory) {
        return factory.createViewBindingHelper(this, 0, variableId, viewModelType);
    }

    @Override
    public void initialize(@NonNull VDB dataBinding, VM viewModel) {
        callback.initialise(this, dataBinding, viewModel);
    }

    public final int getItemViewType() {
        return itemViewType;
    }

    @NonNull
    public final IOptions<?, ViewHolderHelper<VM, VDB>> getOptions() {
        return options;
    }

    @NonNull
    public static <VM extends IViewModel<ViewHolderHelper<VM, VDB>>, VDB extends ViewDataBinding> ViewHolderHelper<VM, VDB> of(@NonNull IExtendView<?, ?, ?> parentContainerView, @NonNull IOptions<?, ViewHolderHelper<VM, VDB>> options, @NonNull View itemView, @NonNull Class<VM> viewModelType, int variableId, int itemViewType, @NonNull IViewHelperCallback<ViewHolderHelper<VM, VDB>, VM, VDB> callback) {
        return new ViewHolderHelper<>(parentContainerView, options, itemView, viewModelType, variableId, itemViewType, callback);
    }

}
