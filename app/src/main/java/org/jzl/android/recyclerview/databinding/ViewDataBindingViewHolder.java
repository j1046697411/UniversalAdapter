package org.jzl.android.recyclerview.databinding;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import org.jzl.android.recyclerview.core.IViewHolder;

import java.util.Objects;

public class ViewDataBindingViewHolder<VDB extends ViewDataBinding> implements IViewHolder, LifecycleOwner {

    private final VDB viewDataBinding;
    private final Lifecycle lifecycle;

    public ViewDataBindingViewHolder(@NonNull View itemView, @NonNull LifecycleOwner lifecycleOwner) {
        this(itemView, lifecycleOwner.getLifecycle());
    }

    public ViewDataBindingViewHolder(@NonNull View itemView, @NonNull Lifecycle lifecycle) {
        this.viewDataBinding = DataBindingUtil.bind(itemView);
        Objects.requireNonNull(viewDataBinding, "viewDataBinding is null");
        this.lifecycle = lifecycle;
        this.viewDataBinding.setLifecycleOwner(this);
    }

    @NonNull
    public VDB getViewDataBinding() {
        return viewDataBinding;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setVariable(int variableId, Object value) {
        this.viewDataBinding.setVariable(variableId, value);
    }

    @NonNull
    private static Lifecycle getAndRequireLifecycle(@NonNull View view) {
        Context context = view.getContext();
        if (context instanceof LifecycleOwner) {
            return ((LifecycleOwner) context).getLifecycle();
        }
        throw new RuntimeException("");
    }

}
