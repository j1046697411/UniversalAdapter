package org.jzl.android.recyclerview.core;

@FunctionalInterface
public
interface IDataClassifier<T, VH extends IViewHolder> {

    int getItemViewType(IConfiguration<T, VH> configuration, T data, int position);

}
