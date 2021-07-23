package org.jzl.android.recyclerview.view;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IView;
import org.jzl.android.mvvm.IViewModel;
import org.jzl.android.mvvm.IViewModelProvider;
import org.jzl.android.mvvm.IViewModelStore;
import org.jzl.android.mvvm.ViewHelper;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.vh.IInitializing;

public abstract class AbstractMVVMViewHolder<V extends IView, VM extends IViewModel<V>, VDB extends ViewDataBinding> implements IViewHolder,
        IExtendView<V, VM, VDB>, LifecycleEventObserver, IInitializing {

    private final ViewHelper<V, VM, VDB> viewHelper = new ViewHelper<>();
    private final IExtendView<?, ?, ?> parentContainerView;
    private final View itemView;

    protected AbstractMVVMViewHolder(@NonNull IExtendView<?, ?, ?> parentContainerView, @NonNull View itemView) {
        this.parentContainerView = parentContainerView;
        this.itemView = itemView;
        parentContainerView.getLifecycle().addObserver(this);
    }

    @Override
    public final void initialize() {
        viewHelper.bind(this, itemView);
    }

    @NonNull
    @Override
    public final IExtendView<?, ?, ?> getParentContainerView() {
        return parentContainerView;
    }

    @NonNull
    @Override
    public final VM getViewModel() {
        return viewHelper.getViewModel();
    }

    @NonNull
    @Override
    public final VDB getViewDataBinding() {
        return viewHelper.getViewDataBinding();
    }

    @NonNull
    @Override
    public final V getSelfView() {
        return viewHelper.getView();
    }

    @Override
    public final Application getApplication() {
        return viewHelper.getView().getApplication();
    }

    @NonNull
    @Override
    public final Lifecycle getLifecycle() {
        return parentContainerView.getLifecycle();
    }

    @NonNull
    @Override
    public final IViewModelProvider getViewModelProvider() {
        return viewHelper.getViewModelProvider();
    }

    @Override
    public final void bindVariable(int variableId, @Nullable Object value) {
        viewHelper.bindVariable(variableId, value);
    }

    @Override
    public void unbind() {
    }

    @Override
    public IViewModelStore getSelfViewModelStore() {
        return viewHelper.getSelfViewModelStore();
    }

    @NonNull
    public View getItemView() {
        return itemView;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            viewHelper.unbind();
        }
    }
}
