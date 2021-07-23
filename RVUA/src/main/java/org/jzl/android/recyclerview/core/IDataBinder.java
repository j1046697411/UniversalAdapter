package org.jzl.android.recyclerview.core;

public interface IDataBinder<T, VH extends IViewHolder> {

    void binding(IContext context, VH viewHolder, T data);

}
