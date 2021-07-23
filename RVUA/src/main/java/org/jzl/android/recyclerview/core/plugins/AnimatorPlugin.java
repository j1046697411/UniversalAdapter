package org.jzl.android.recyclerview.core.plugins;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.text.util.Linkify;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IAnimatorFactory;
import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IConfigurationBuilder;
import org.jzl.android.recyclerview.core.IPlugin;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.lang.util.ObjectUtils;

public class AnimatorPlugin<T, VH extends IViewHolder> implements IPlugin<T, VH> {

    private IAnimatorFactory<VH> animatorFactory;
    private final IBindPolicy bindPolicy;
    private int lastPosition;
    private long duration = 300;
    private TimeInterpolator interpolator = new LinearInterpolator();
    private long delay = 0;
    private boolean animationFirstOnly = false;

    private AnimatorPlugin(@NonNull IAnimatorFactory<VH> animatorFactory, @NonNull IBindPolicy bindPolicy) {
        this.animatorFactory = animatorFactory;
        this.bindPolicy = bindPolicy;
    }

    @Override
    public void setup(@NonNull IConfigurationBuilder<T, VH> builder) {
        builder.addViewAttachedToWindowListener((options, owner) -> {
            if (animationFirstOnly || lastPosition < owner.getContext().getAdapterPosition()) {
                Animator animator = animatorFactory.animator(owner);
                animator.setDuration(duration);
                animator.setInterpolator(interpolator);
                animator.setStartDelay(delay);
                animator.start();
                lastPosition = owner.getContext().getAdapterPosition();
            }
        }, bindPolicy);
    }

    @NonNull
    public AnimatorPlugin<T, VH> setInterpolator(@NonNull TimeInterpolator interpolator) {
        this.interpolator = ObjectUtils.get(interpolator, this.interpolator);
        return this;
    }

    @NonNull
    public AnimatorPlugin<T, VH> setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @NonNull
    public AnimatorPlugin<T, VH> setDelay(long delay) {
        this.delay = delay;
        return this;
    }

    @NonNull
    public AnimatorPlugin<T, VH> setAnimatorFactory(IAnimatorFactory<VH> animatorFactory) {
        lastPosition = -1;
        this.animatorFactory = ObjectUtils.get(animatorFactory, this.animatorFactory);
        return this;
    }

    public AnimatorPlugin<T, VH> setAnimationFirstOnly(boolean animationFirstOnly) {
        this.animationFirstOnly = animationFirstOnly;
        return this;
    }

    @NonNull
    public static <T, VH extends IViewHolder> AnimatorPlugin<T, VH> of(@NonNull IAnimatorFactory<VH> animatorFactory, @NonNull IBindPolicy bindPolicy) {
        return new AnimatorPlugin<>(animatorFactory, bindPolicy);
    }

    public static <T, VH extends IViewHolder> AnimatorPlugin<T, VH> alphaIn(float mFromValue, @NonNull IBindPolicy bindPolicy) {
        return of(IAnimatorFactory.alphaIn(mFromValue), bindPolicy);
    }

    public static <T, VH extends IViewHolder> AnimatorPlugin<T, VH> scaleIn(@NonNull IAnimatorFactory.ScaleType scaleType, float fromValue, @NonNull IBindPolicy bindPolicy) {
        return of(IAnimatorFactory.scaleIn(scaleType, fromValue), bindPolicy);
    }

    public static <T, VH extends IViewHolder> AnimatorPlugin<T, VH> slideInBottom(@NonNull IBindPolicy bindPolicy) {
        return of(IAnimatorFactory.slideInBottom(), bindPolicy);
    }

    public static <T, VH extends IViewHolder> AnimatorPlugin<T, VH> slideInLeft(@NonNull IBindPolicy bindPolicy) {
        return of(IAnimatorFactory.slideInLeft(), bindPolicy);
    }

    public static <T, VH extends IViewHolder> AnimatorPlugin<T, VH> slideInRight(@NonNull IBindPolicy bindPolicy) {
        return of(IAnimatorFactory.slideInRight(), bindPolicy);
    }

}
