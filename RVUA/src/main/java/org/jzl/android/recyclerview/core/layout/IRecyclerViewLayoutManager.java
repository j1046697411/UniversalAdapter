package org.jzl.android.recyclerview.core.layout;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.model.ISpanSizable;

public interface IRecyclerViewLayoutManager<T, VH extends IViewHolder> {

    void layoutManager(@NonNull ILayoutManagerFactory<T, VH> layoutManagerFactory);

    void setSpanSizeLookup(@NonNull ISpanSizeLookup<T, VH> spanSizeLookup);

    void spanSize(@NonNull ISpanSizable spanSizable, @NonNull IMatchPolicy matchPolicy, int priority);

}
