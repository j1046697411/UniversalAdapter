package org.jzl.android.recyclerview.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IViewModel;
import org.jzl.android.mvvm.view.helper.IViewHelperCallback;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolderFactory;

public class MVVMViewHolderFactory<VM extends IViewModel<ViewHolderHelper<VM, VDB>>, VDB extends ViewDataBinding> implements IViewHolderFactory<ViewHolderHelper<VM, VDB>> {

    private final IExtendView<?, ?, ?> parentContainerView;
    private final Class<VM> viewModelType;
    private final int variableId;
    private final IViewHelperCallback<ViewHolderHelper<VM, VDB>, VM, VDB> callback;

    public MVVMViewHolderFactory(@NonNull IExtendView<?, ?, ?> parentContainerView,@NonNull Class<VM> viewModelType, int variableId,@NonNull IViewHelperCallback<ViewHolderHelper<VM, VDB>, VM, VDB> callback) {
        this.parentContainerView = parentContainerView;
        this.viewModelType = viewModelType;
        this.variableId = variableId;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolderHelper<VM, VDB> createViewHolder(@NonNull IOptions<?, ViewHolderHelper<VM, VDB>> options, @NonNull View itemView, int itemViewType) {
        return new ViewHolderHelper<>(parentContainerView, options, itemView, viewModelType, variableId, itemViewType, callback);
    }

}
