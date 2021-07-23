package org.jzl.android.recyclerview.core;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.annotation.NonNull;

public interface IAnimatorFactory<VH extends IViewHolder> {

    @NonNull
    Animator animator(@NonNull IViewHolderOwner<VH> owner);

    static <VH extends IViewHolder> IAnimatorFactory<VH> alphaIn(float mFromValue) {
        return owner -> ObjectAnimator.ofFloat(owner.getItemView(), View.ALPHA, mFromValue, 1);
    }

    @NonNull
    static <VH extends IViewHolder> IAnimatorFactory<VH> scaleIn(@NonNull ScaleType scaleType, float fromValue) {
        return owner -> {
            switch (scaleType) {
                case X: {
                    return ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_X, fromValue, 1);
                }
                case Y: {
                    return ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_Y, fromValue, 1);
                }
                case XY: {
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(
                            ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_Y, fromValue, 1),
                            ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_X, fromValue, 1)
                    );
                    return set;
                }
            }
            return ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_X, fromValue, 1);

        };
    }

    @NonNull
    static <VH extends IViewHolder> IAnimatorFactory<VH> slideInBottom() {
        return owner -> ObjectAnimator.ofFloat(owner.getItemView(), View.TRANSLATION_Y, owner.getItemView().getMeasuredHeight(), 0);
    }

    @NonNull
    static <VH extends IViewHolder> IAnimatorFactory<VH> slideInLeft() {
        return owner -> ObjectAnimator.ofFloat(owner.getItemView(), View.TRANSLATION_X, -owner.getItemView().getMeasuredWidth(), 0);
    }

    @NonNull
    static <VH extends IViewHolder> IAnimatorFactory<VH> slideInRight() {
        return owner -> ObjectAnimator.ofFloat(owner.getItemView(), View.TRANSLATION_X, owner.getItemView().getMeasuredWidth(), 0);
    }

    @NonNull
    static <VH extends IViewHolder> IAnimatorFactory<VH> zoom(float zoom) {
        return owner -> {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_Y, 1, zoom, 1),
                    ObjectAnimator.ofFloat(owner.getItemView(), View.SCALE_X, 1, zoom, 1)
            );
            return animatorSet;
        };
    }

    enum ScaleType {
        X, Y, XY
    }

}
