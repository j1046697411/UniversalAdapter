package org.jzl.android.mvvm.view.helper;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IViewModel;

@FunctionalInterface
public interface IViewHelperCallback<V extends IExtendView<V, VM, VDB>, VM extends IViewModel<V>, VDB extends ViewDataBinding> {

    void initialise(@NonNull V view, @NonNull VDB viewDataBinding, @NonNull VM viewModel);

    default void unbind() {
    }
}
