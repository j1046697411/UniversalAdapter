package org.jzl.android.recyclerview.core.module;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;

public interface IModule<T, VH extends IViewHolder> {

    @NonNull
    IOptions<T, VH> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<T> dataGetter);

}
