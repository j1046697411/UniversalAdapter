package org.jzl.android.recyclerview.core;

import androidx.annotation.NonNull;

public interface IDataGetterOwner<T> {

    @NonNull
    IDataGetter<T> getDataGetter();

}
