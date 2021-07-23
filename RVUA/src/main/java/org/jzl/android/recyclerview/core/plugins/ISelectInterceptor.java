package org.jzl.android.recyclerview.core.plugins;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;
import org.jzl.android.recyclerview.model.ISelectable;

public interface ISelectInterceptor<T extends ISelectable, VH extends IViewHolder> {

    void intercept(IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner, ISelector<T> selector);

}