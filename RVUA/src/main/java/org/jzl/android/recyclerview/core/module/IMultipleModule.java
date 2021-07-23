package org.jzl.android.recyclerview.core.module;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IOptionsBuilder;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.util.Functions;
import org.jzl.lang.fun.Function;

public interface IMultipleModule<T, VH extends IViewHolder> extends IModule<T, VH> {

    <T1, VH1 extends VH> void registered(@NonNull IModule<T1, VH1> module, @NonNull Function<T, T1> mapper);

    default <VH1 extends VH> void registered(@NonNull IModule<T, VH1> module) {
        registered(module, Functions.own());
    }

    void registered(@NonNull IMultipleModule.IRegistrar<T, VH> registrar);

    interface IRegistrar<T, VH extends IViewHolder> {

        void registered(@NonNull IConfiguration<?, ?> configuration, @NonNull IOptionsBuilder<T, VH> optionsBuilder, @NonNull IDataGetter<T> dataGetter);
    }
}
