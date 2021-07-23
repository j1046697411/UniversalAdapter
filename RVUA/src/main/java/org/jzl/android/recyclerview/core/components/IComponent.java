package org.jzl.android.recyclerview.core.components;

import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;

public interface IComponent<T, VH extends IViewHolder> {

    void initialize(IConfiguration<T, VH> configuration);

}
