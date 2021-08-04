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
import org.jzl.android.recyclerview.core.listeners.OnClickItemViewListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClickAnimationProxy<T, VH extends IViewHolder> implements OnClickItemViewListener<T, VH> {

    private final IAnimatorFactory<VH> animatorFactory;
    private final OnClickItemViewListener<T, VH> clickItemViewListener;
    private long startDelay;
    private TimeInterpolator interpolator = new LinearInterpolator();
    private long duration = 400;
    private final AtomicBoolean playing = new AtomicBoolean(false);


    private ClickAnimationProxy(IAnimatorFactory<VH> animatorFactory, OnClickItemViewListener<T, VH> clickItemViewListener) {
        this.animatorFactory = animatorFactory;
        this.clickItemViewListener = clickItemViewListener;
    }

    @Override
    public void onClickItemView(@NonNull IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner) {
        if (playing.compareAndSet(false, true)) {
            Animator animator = animatorFactory.animator(viewHolderOwner);
            animator.setDuration(duration);
            animator.setInterpolator(interpolator);
            animator.setStartDelay(startDelay);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    clickItemViewListener.onClickItemView(options, viewHolderOwner);
                    playing.set(false);
                }
            });
            animator.start();
        }
    }

    public ClickAnimationProxy<T, VH> setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public ClickAnimationProxy<T, VH> setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public ClickAnimationProxy<T, VH> setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public static <T, VH extends IViewHolder> ClickAnimationProxy<T, VH> of(@NonNull IAnimatorFactory<VH> animatorFactory, @NonNull OnClickItemViewListener<T, VH> clickItemViewListener) {
        return new ClickAnimationProxy<>(animatorFactory, clickItemViewListener);
    }

    public static <T, VH extends IViewHolder> ClickAnimationProxy<T, VH> zoom(float zoom, OnClickItemViewListener<T, VH> clickItemViewListener) {
        return of(IAnimatorFactory.zoom(zoom), clickItemViewListener);
    }

}
