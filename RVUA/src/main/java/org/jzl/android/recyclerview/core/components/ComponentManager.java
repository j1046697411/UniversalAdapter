package org.jzl.android.recyclerview.core.components;

import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.lang.util.ForeachUtils;

import java.util.ArrayList;
import java.util.List;

public class ComponentManager<T, VH extends IViewHolder> implements IComponentManager<T, VH> {

    private final List<IComponent<T, VH>> components = new ArrayList<>();

    @Override
    public void addComponent(IComponent<T, VH> component) {
        if (!components.contains(component)) {
            components.add(component);
        }
    }

    @Override
    public void initialize(IConfiguration<T, VH> configuration) {
        ForeachUtils.each(this.components, target -> target.initialize(configuration));
    }
}
