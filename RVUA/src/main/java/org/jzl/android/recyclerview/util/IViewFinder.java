package org.jzl.android.recyclerview.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@FunctionalInterface
public interface IViewFinder {

    /**
     * 根据id获取对应的view对象
     * @param id 需要查询的viewId
     * @param <V> 查询的View对象类型
     * @return 查询出来的view对象， 该对象可能为null
     */
    <V extends View> V findViewById(@IdRes int id);

    /**
     * 获取{@link ViewBinder}对象
     * @return {@link ViewBinder}
     */
    @NonNull
    default ViewBinder binder() {
        return new ViewBinder(cache());
    }

    /**
     * 把该对象转换为带缓存的{@link IViewFinder} 对象，如果该对象带有缓存，则返回原对象
     * @return 获取的对象
     */
    @NonNull
    default IViewFinder cache() {
        return new CacheViewFinder(this);
    }

    /**
     * {@link IViewFinder} 的 {@link Activity} 构造方法
     * @param activity 目标activity
     * @return 对应的 {@link IViewFinder} 对象
     */
    @NonNull
    static IViewFinder of(@NonNull Activity activity) {
        return activity::findViewById;
    }

    /**
     * {@link IViewFinder} 的 {@link Dialog} 构造方法
     * @param dialog 目标 {@link Dialog}
     * @return 对应的 {@link IViewFinder} 对象
     */
    @NonNull
    static IViewFinder of(@NonNull Dialog dialog) {
        return dialog::findViewById;
    }

    /**
     * {@link IViewFinder} 的 {@link View} 构造方法
     * @param view 目标 {@link View}
     * @return 对应的 {@link IViewFinder} 对象
     */
    @NonNull
    static IViewFinder of(@NonNull View view) {
        return view::findViewById;
    }

}