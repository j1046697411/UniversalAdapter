package org.jzl.android.recyclerview.core;

public interface IDataBinderStore<T, VH extends IViewHolder> extends IDataBinderOwner<T, VH> {

    IDataBinderOwner<T, VH> get(IContext context);

}
