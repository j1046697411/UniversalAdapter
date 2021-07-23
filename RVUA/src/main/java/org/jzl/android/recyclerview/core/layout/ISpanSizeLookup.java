package org.jzl.android.recyclerview.core.layout;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IViewHolder;

public interface ISpanSizeLookup<T, VH extends IViewHolder> {

    int getSpanSize(@NonNull IConfiguration<T, VH> configuration, int spanCount, int position);

}
