package org.jzl.android.recyclerview.core.listeners.proxy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IAnimatorFactory;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;
import org.jzl.android.recyclerview.core.listeners.OnLongClickItemViewListener;
import org.jzl.lang.util.ObjectUtils;

import java.util.concurrent.atomic.AtomicBoolean;

public class LongClickAnimatorProxy<T, VH extends IViewHolder> implements OnLongClickItemViewListener<T, VH> {

    private final IAnimatorFactory<VH> animatorFactory;
    private final OnLongClickItemViewListener<T, VH> longClickItemViewListener;
    private TimeInterpolator interpolator = new LinearInterpolator();
    private long duration = 400;
    private final AtomicBoolean playing = new AtomicBoolean(false);

    public LongClickAnimatorProxy(IAnimatorFactory<VH> animatorFactory, OnLongClickItemViewListener<T, VH> longClickItemViewListener) {
        this.animatorFactory = animatorFactory;
        this.longClickItemViewListener = longClickItemViewListener;
    }

    @Override
    public boolean onLongClickItemView(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner) {
        if (playing.compareAndSet(false, true)) {
            Animator animator = animatorFactory.animator(viewHolderOwner);
            animator.setDuration(duration);
            animator.setInterpolator(interpolator);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    longClickItemViewListener.onLongClickItemView(options, viewHolderOwner);
                    playing.set(false);
                }
            });
            animator.start();
            return true;
        } else {
            return false;
        }
    }

    public LongClickAnimatorProxy<T, VH> setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public LongClickAnimatorProxy<T, VH> setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = ObjectUtils.get(interpolator, this.interpolator);
        return this;
    }

    @NonNull
    public static <T, VH extends IViewHolder> LongClickAnimatorProxy<T, VH> of(@NonNull IAnimatorFactory<VH> animatorFactory, @NonNull OnLongClickItemViewListener<T, VH> longClickItemViewListener) {
        return new LongClickAnimatorProxy<>(animatorFactory, longClickItemViewListener);
    }

    @NonNull
    public static <T, VH extends IViewHolder> LongClickAnimatorProxy<T, VH> zoom(float zoom, @NonNull OnLongClickItemViewListener<T, VH> longClickItemViewListener) {
        return of(IAnimatorFactory.zoom(zoom), longClickItemViewListener);
    }
}
