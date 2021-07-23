package org.jzl.android.recyclerview.core.databinding;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import org.jzl.android.recyclerview.core.IViewHolder;

public class DataBindingViewHolder<VDB extends ViewDataBinding> implements IViewHolder {

    private final VDB dataBinding;

    public DataBindingViewHolder(View itemView) {
        this.dataBinding = DataBindingUtil.bind(itemView);
    }

    public VDB getDataBinding() {
        return dataBinding;
    }
}
