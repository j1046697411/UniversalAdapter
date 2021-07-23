package org.jzl.android.recyclerview.core.plugins;

import androidx.annotation.NonNull;
import org.jzl.android.recyclerview.model.ISelectable;
import org.jzl.lang.fun.Predicate;

import java.util.Collection;
import java.util.List;

public interface ISelector<T extends ISelectable> {

    void checked(int position, boolean checked);

    void checked(T data, boolean checked);

    void checkedAll();

    void checkedAll(@NonNull Predicate<T> predicate);

    void uncheckedAll();

    void reverseAll();

    @NonNull
    <C extends Collection<T>> C getSelectResult(@NonNull C result);

    @NonNull
    List<T> getSelectResult();
}
