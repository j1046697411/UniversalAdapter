package org.jzl.android.recyclerview.util;


import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.model.UniversalModel;
import org.jzl.lang.fun.Function;

public class Functions {

    private static final Function<Object, Object> MAPPER = target -> target;
    private static final Function<UniversalModel, Object> UNIVERSAL = UniversalModel::getData;

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T> Function<T, T> own() {
        return (Function<T, T>) MAPPER;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T> Function<UniversalModel, T> universal() {
        return (Function<UniversalModel, T>) UNIVERSAL;
    }
}
