package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;

public interface IPlugin<T, VH extends IViewHolder> {

    void setup(@NonNull IConfigurationBuilder<T, VH> builder);
}
