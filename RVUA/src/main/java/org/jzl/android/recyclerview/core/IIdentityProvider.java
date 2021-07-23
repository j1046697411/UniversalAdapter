package org.jzl.android.recyclerview.core;

@FunctionalInterface
public
interface IIdentityProvider<T, VH extends IViewHolder> {

    long getItemId(IConfiguration<T, VH> configuration, T data, int position);

}
