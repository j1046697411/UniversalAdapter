package org.jzl.android.recyclerview.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.util.Linkify;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.listeners.observer.IObservable;
import org.jzl.lang.util.ForeachUtils;
import org.jzl.lang.util.ObjectUtils;

public final class ViewBinder implements IViewFinder {

    private final IViewFinder viewFinder;

    ViewBinder(@NonNull IViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    @Override
    public <V extends View> V findViewById(int id) {
        return viewFinder.findViewById(id);
    }

    @NonNull
    @Override
    public IViewFinder cache() {
        return viewFinder.cache();
    }

    @NonNull
    @Override
    public ViewBinder binder() {
        return this;
    }

    @NonNull
    public ViewBinder setText(@IdRes int id, @StringRes int stringId) {
        this.<TextView>findViewById(id).setText(stringId);
        return this;
    }

    @NonNull
    public ViewBinder setText(@IdRes int id, CharSequence text) {
        this.<TextView>findViewById(id).setText(text);
        return this;
    }

    @NonNull
    public ViewBinder linkify(@IdRes int id) {
        Linkify.addLinks(this.<TextView>findViewById(id), Linkify.ALL);
        return this;
    }

    @NonNull
    public ViewBinder setTextColor(@IdRes int id, @ColorInt int color) {
        this.<TextView>findViewById(id).setTextColor(color);
        return this;
    }

    @NonNull
    public ViewBinder setBackground(@IdRes int id, Drawable background) {
        this.findViewById(id).setBackground(background);
        return this;
    }

    @NonNull
    public ViewBinder setBackground(@IdRes int id, @DrawableRes int backgroundId) {
        findViewById(id).setBackgroundResource(backgroundId);
        return this;
    }

    @NonNull
    public ViewBinder setBackgroundColor(@IdRes int id, @ColorInt int colorId) {
        findViewById(id).setBackgroundColor(colorId);
        return this;
    }

    @NonNull
    public ViewBinder setImageResource(@IdRes int id, @DrawableRes int resId) {
        this.<ImageView>findViewById(id).setImageResource(resId);
        return this;
    }

    @NonNull
    public ViewBinder setImageBitmap(@IdRes int id, @NonNull Bitmap bitmap) {
        this.<ImageView>findViewById(id).setImageBitmap(bitmap);
        return this;
    }

    @NonNull
    public ViewBinder setImageDrawable(@IdRes int id, @NonNull Drawable drawable) {
        this.<ImageView>findViewById(id).setImageDrawable(drawable);
        return this;
    }

    @NonNull
    public ViewBinder setVisibility(@IdRes int id, @Visibility int visibility) {
        findViewById(id).setVisibility(visibility);
        return this;
    }

    @NonNull
    public ViewBinder setChecked(@IdRes int id, boolean checked) {
        this.<CompoundButton>findViewById(id).setChecked(checked);
        return this;
    }

    @NonNull
    public ViewBinder setProgress(@IdRes int id, int progress) {
        this.<ProgressBar>findViewById(id).setProgress(progress);
        return this;
    }

    @NonNull
    public ViewBinder setProgressMax(@IdRes int id, int max) {
        this.<ProgressBar>findViewById(id).setMax(max);
        return this;
    }

    @NonNull
    public ViewBinder steRating(@IdRes int id, int rating) {
        this.<RatingBar>findViewById(id).setRating(rating);
        return this;
    }

    @NonNull
    public ViewBinder steRating(@IdRes int id, int rating, int max) {
        RatingBar ratingBar = findViewById(id);
        ratingBar.setRating(rating);
        ratingBar.setMax(max);
        return this;
    }

    @NonNull
    public ViewBinder setTag(@IdRes int id, Object tag) {
        this.findViewById(id).setTag(tag);
        return this;
    }

    @NonNull
    public ViewBinder setTag(@IdRes int id, int key, Object tag) {
        findViewById(id).setTag(key, tag);
        return this;
    }

    @NonNull
    public ViewBinder bindTouchListener(@IdRes int id, @NonNull View.OnTouchListener touchListener) {
        findViewById(id).setOnTouchListener(touchListener);
        return this;
    }

    @NonNull
    public ViewBinder bindLongClickListener(@IdRes int id, @NonNull View.OnLongClickListener longClickListener) {
        findViewById(id).setOnLongClickListener(longClickListener);
        return this;
    }

    public ViewBinder bindLongClickListeners(View.OnLongClickListener longClickListener, @IdRes int... ids) {
        ForeachUtils.each(ids, (index, target) -> bindLongClickListener(target, longClickListener));
        return this;
    }

    @NonNull
    public ViewBinder bindClickListener(@IdRes int id, @NonNull View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
        return this;
    }

    @NonNull
    public ViewBinder bindClickListeners(@NonNull View.OnClickListener listener, @IdRes int... ids) {
        ForeachUtils.each(ids, (index, target) -> bindClickListener(target, listener));
        return this;
    }

    @NonNull
    public ViewBinder bindCheckedChangeListener(@IdRes int id, @NonNull CompoundButton.OnCheckedChangeListener listener) {
        this.<CompoundButton>findViewById(id).setOnCheckedChangeListener(listener);
        return this;
    }

    @NonNull
    public ViewBinder addClickListener(@IdRes int id, @NonNull View.OnClickListener clickListener) {
        return addClickListener(findViewById(id), clickListener);
    }

    @NonNull
    public ViewBinder addClickListeners(View.OnClickListener clickListener, @IdRes int... ids) {
        ForeachUtils.each(ids, (index, target) -> addClickListener(target, clickListener));
        return this;
    }

    @NonNull
    @SuppressWarnings("all")
    public ViewBinder addClickListener(@NonNull View view, @NonNull View.OnClickListener clickListener) {
        IObservable<View.OnClickListener> observable = (IObservable<View.OnClickListener>) view.getTag(R.id.tag_click);
        if (ObjectUtils.isNull(observable)) {
            observable = IObservable.click(view);
            view.setTag(R.id.tag_click, observable);
        }
        observable.observe(clickListener);
        return this;
    }

    @NonNull
    public ViewBinder addLongClickListener(@IdRes int id, @NonNull View.OnLongClickListener longClickListener) {
        return addLongClickListener(findViewById(id), longClickListener);
    }

    @SuppressWarnings("all")
    @NonNull
    public ViewBinder addLongClickListener(@NonNull View view, @NonNull View.OnLongClickListener longClickListener) {
        IObservable<View.OnLongClickListener> observable = (IObservable<View.OnLongClickListener>) view.getTag(R.id.tag_long_click);
        if (ObjectUtils.isNull(observable)) {
            observable = IObservable.longClick(view);
            view.setTag(R.id.tag_long_click, observable);
        }
        observable.observe(longClickListener);
        return this;
    }

    @NonNull
    public ViewBinder addLongClickListeners(@NonNull View.OnLongClickListener longClickListener, int... ids) {
        ForeachUtils.each(ids, (index, target) -> addLongClickListener(target, longClickListener));
        return this;
    }

}
