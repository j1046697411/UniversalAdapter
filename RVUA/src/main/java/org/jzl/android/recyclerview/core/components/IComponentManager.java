package org.jzl.android.recyclerview.core.components;

import org.jzl.android.recyclerview.core.IViewHolder;

public interface IComponentManager<T, VH extends IViewHolder> extends IComponent<T, VH> {

    void addComponent(IComponent<T, VH> component);

}
