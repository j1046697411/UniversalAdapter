package org.jzl.android.recyclerview.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.*;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.listeners.observer.IObservable;
import org.jzl.lang.util.ForeachUtils;
import org.jzl.lang.util.ObjectUtils;

/**
 * {@link View}
 */
public final class ViewBinder implements IViewFinder {

    private final IViewFinder viewFinder;

    ViewBinder(@NonNull IViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    /**
     * @see IViewFinder#findViewById(int)
     */
    @Override
    public <V extends View> V findViewById(int id) {
        return viewFinder.findViewById(id);
    }

    /**
     * @see IViewFinder#cache()
     */

    @NonNull
    @Override
    public IViewFinder cache() {
        return viewFinder.cache();
    }

    /**
     * @see IViewFinder#binder()
     */
    @NonNull
    @Override
    public ViewBinder binder() {
        return this;
    }

    /**
     * 给 {@link TextView} 设置Text
     *
     * @param id       对应的{@link TextView} 的 id
     * @param stringId 对应的String资源id
     * @see TextView#setText(int)
     */
    @NonNull
    public ViewBinder setText(@IdRes int id, @StringRes int stringId) {
        this.<TextView>findViewById(id).setText(stringId);
        return this;
    }

    /**
     * 给 {@link TextView} 设置Text
     *
     * @param id   对应的{@link TextView} 的 id
     * @param text 对应的String的值
     * @see TextView#setText(CharSequence)
     */
    @NonNull
    public ViewBinder setText(@IdRes int id, CharSequence text) {
        this.<TextView>findViewById(id).setText(text);
        return this;
    }

    /**
     * 修改{@link TextView} 的 Links类型
     *
     * @param id      对应的{@link TextView} 的 id
     * @param linkify {@link Linkify#ALL}, {@link Linkify#WEB_URLS}, {@link Linkify#EMAIL_ADDRESSES}, {@link Linkify#PHONE_NUMBERS} , {@link Linkify#MAP_ADDRESSES}
     * @see Linkify#ALL
     * @see Linkify#addLinks(TextView, int)
     */
    @NonNull
    public ViewBinder linkify(@IdRes int id, int linkify) {
        Linkify.addLinks(this.<TextView>findViewById(id), linkify);
        return this;
    }

    /**
     * 修改{@link TextView} 的Links类型为 {@link Linkify#ALL}
     */
    @NonNull
    public ViewBinder linkify(@IdRes int id) {
        return linkify(id, Linkify.ALL);
    }

    /**
     * 设置{@link TextView} 的字体颜色
     *
     * @param id    对应的{@link TextView} 的 id
     * @param color 修改的颜色值
     * @see TextView#setTextColor(int)
     */
    @NonNull
    public ViewBinder setTextColor(@IdRes int id, @ColorInt int color) {
        this.<TextView>findViewById(id).setTextColor(color);
        return this;
    }

    /**
     * 设置 {@link View} 的背景图片
     *
     * @param id         {@link View} 的 id
     * @param background 需要设置的背景图片
     * @see View#setBackground(Drawable)
     */
    @NonNull
    public ViewBinder setBackground(@IdRes int id, @NonNull Drawable background) {
        this.findViewById(id).setBackground(background);
        return this;
    }

    /**
     * 设置 {@link View} 的背景资源
     *
     * @param id           {@link View} 的 id
     * @param backgroundId 需要设置的资源id
     * @see View#setBackgroundResource(int)
     */
    @NonNull
    public ViewBinder setBackground(@IdRes int id, @DrawableRes int backgroundId) {
        findViewById(id).setBackgroundResource(backgroundId);
        return this;
    }

    /**
     * 设置 {@link View} 的背景颜色
     *
     * @param id      {@link View} 的 id
     * @param colorId 需要设置的颜色资源id
     * @see View#setBackgroundColor(int)
     */
    @NonNull
    public ViewBinder setBackgroundColor(@IdRes int id, @ColorInt int colorId) {
        findViewById(id).setBackgroundColor(colorId);
        return this;
    }

    /**
     * 设置{@link ImageView} 的图片资源
     *
     * @param id    {@link ImageView} 的 id
     * @param resId 图片资源id
     * @see ImageView#setImageResource(int)
     */
    @NonNull
    public ViewBinder setImageResource(@IdRes int id, @DrawableRes int resId) {
        this.<ImageView>findViewById(id).setImageResource(resId);
        return this;
    }

    /**
     * 设置{@link ImageView} 的图片对象
     *
     * @param id     {@link ImageView} 的 id
     * @param bitmap 图片对象
     * @see ImageView#setImageBitmap(Bitmap)
     */
    @NonNull
    public ViewBinder setImageBitmap(@IdRes int id, @NonNull Bitmap bitmap) {
        this.<ImageView>findViewById(id).setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置{@link ImageView} 的图片对象
     *
     * @param id       {@link ImageView} 的 id
     * @param drawable 图片对象
     * @see ImageView#setImageDrawable(Drawable)
     */
    @NonNull
    public ViewBinder setImageDrawable(@IdRes int id, @NonNull Drawable drawable) {
        this.<ImageView>findViewById(id).setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置 @{@link View} 的 Visibility属性
     *
     * @param id         {@link View} 的 id
     * @param visibility {@link View#GONE} , {@link View#VISIBLE} , {@link View#INVISIBLE}
     * @see View#setVisibility(int)
     */

    @NonNull
    public ViewBinder setVisibility(@IdRes int id, @Visibility int visibility) {
        findViewById(id).setVisibility(visibility);
        return this;
    }

    /**
     * 设置 {@link CompoundButton} 对象的 checked属性
     *
     * @param id      {@link CompoundButton} 的 id
     * @param checked 对应的值
     * @see CompoundButton#setChecked(boolean)
     */
    @NonNull
    public ViewBinder setChecked(@IdRes int id, boolean checked) {
        this.<CompoundButton>findViewById(id).setChecked(checked);
        return this;
    }

    /**
     * 设置 {@link View} 的 Focusable 状态
     *
     * @param id        {@link View} 的 id
     * @param focusable 对应的值
     * @see View#setFocusable(boolean)
     */
    @NonNull
    public ViewBinder setFocusable(@IdRes int id, boolean focusable) {
        this.findViewById(id).setFocusable(focusable);
        return this;
    }

    /**
     * 设置 {@link View} 的 Focusable 状态
     *
     * @param id        {@link View} 的 id
     * @param focusable {@link View#FOCUSABLE}, {@link View#NOT_FOCUSABLE}, {@link View#FOCUSABLE_AUTO}
     * @see View#setFocusable(int)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ViewBinder setFocusable(@IdRes int id, int focusable) {
        this.findViewById(id).setFocusable(focusable);
        return this;
    }


    /**
     * 设置 {@link View} 的可点击性
     *
     * @param id        {@link View} 的 id
     * @param clickable 是否可以点击
     * @see View#setClickable(boolean)
     */
    @NonNull
    public ViewBinder setClickable(@IdRes int id, boolean clickable) {
        this.findViewById(id).setClickable(clickable);
        return this;
    }

    /**
     * 设置进度条的进度
     * @param id {@link ProgressBar} 的 id
     * @param progress 进度值
     * @see ProgressBar#setProgress(int)
     */
    @NonNull
    public ViewBinder setProgress(@IdRes int id, int progress) {
        this.<ProgressBar>findViewById(id).setProgress(progress);
        return this;
    }

    /**
     * 设置进度条的进度
     * @param id {@link ProgressBar} 的 id
     * @param progress 进度值
     * @param anim 是否显示动画
     * @see ProgressBar#setProgress(int, boolean) 
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ViewBinder setProgress(@IdRes int id, int progress, boolean anim){
        this.<ProgressBar>findViewById(id).setProgress(progress, anim);
        return this;
    }

    /**
     * 设置进度条最大值
     * @param id {@link ProgressBar} 的 id
     * @param max 需要设置的最大值
     * @see ProgressBar#setMax(int)
     */
    @NonNull
    public ViewBinder setProgressMax(@IdRes int id, int max) {
        this.<ProgressBar>findViewById(id).setMax(max);
        return this;
    }


    /**
     * 设置评级星数
     * @param id {@link RatingBar} 的 id
     * @param rating 星数
     * @see RatingBar#setRating(float)
     */
    @NonNull
    public ViewBinder steRating(@IdRes int id, int rating) {
        this.<RatingBar>findViewById(id).setRating(rating);
        return this;
    }

    /**
     * 设置评级星数
     * @param id {@link RatingBar} 的 id
     * @param rating 星数
     * @param max 最大星数
     * @see RatingBar#setMax(int) 
     * @see RatingBar#setRating(float)
     */
    @NonNull
    public ViewBinder steRating(@IdRes int id, int rating, int max) {
        RatingBar ratingBar = findViewById(id);
        ratingBar.setRating(rating);
        ratingBar.setMax(max);
        return this;
    }

    /**
     * 设置{@link View} 的 Tag
     * @param id {@link View} 的 id
     * @param tag 需要设置的tag值
     * @see View#setTag(Object)
     */
    @NonNull
    public ViewBinder setTag(@IdRes int id, Object tag) {
        this.findViewById(id).setTag(tag);
        return this;
    }

    /**
     * 设置{@link View} 的 Tag
     * @param id {@link View} 的 id
     * @param key 需要设置的tag的key
     * @param tag 需要设置的tag值
     * @see View#setTag(int, Object)
     */
    @NonNull
    public ViewBinder setTag(@IdRes int id, int key, Object tag) {
        findViewById(id).setTag(key, tag);
        return this;
    }

    /**
     * 给 {@link View} 绑定一个触摸事件
     * @param id 需要绑定事件的{@link View} id
     * @param touchListener 需要绑定的事件
     * @see View#setOnTouchListener(View.OnTouchListener)
     */
    @NonNull
    public ViewBinder bindTouchListener(@IdRes int id, @NonNull View.OnTouchListener touchListener) {
        findViewById(id).setOnTouchListener(touchListener);
        return this;
    }

    /**
     * 给多个 {@link View} 绑定一个触摸事件
     * @param touchListener 需要绑定的事件
     * @param ids 需要绑定事件的多个{@link View} id
     * @see View#setOnTouchListener(View.OnTouchListener)
     */
    @NonNull
    public ViewBinder bindTouchListener(@NonNull View.OnTouchListener touchListener,@IdRes int ... ids){
        ForeachUtils.each(ids, (index, target) -> bindTouchListener(touchListener, target));
        return this;
    }

    /**
     * 给 {@link View} 绑定一个长按事件
     * @param id 需要绑定事件的{@link View} id
     * @param longClickListener 需要绑定的事件
     * @see View#setOnLongClickListener(View.OnLongClickListener)
     */
    @NonNull
    public ViewBinder bindLongClickListener(@IdRes int id, @NonNull View.OnLongClickListener longClickListener) {
        findViewById(id).setOnLongClickListener(longClickListener);
        return this;
    }

    /**
     * 给多个 {@link View} 绑定一个长按事件
     * @param longClickListener 需要绑定的事件
     * @param ids 需要绑定事件的多个{@link View} id
     * @see View#setOnLongClickListener(View.OnLongClickListener)
     */
    public ViewBinder bindLongClickListeners(View.OnLongClickListener longClickListener, @IdRes int... ids) {
        ForeachUtils.each(ids, (index, target) -> bindLongClickListener(target, longClickListener));
        return this;
    }

    /**
     * 给 {@link View} 绑定一个点击事件
     * @param id 需要绑定事件的{@link View} id
     * @param listener 需要绑定的事件
     * @see View#setOnClickListener(View.OnClickListener)
     */
    @NonNull
    public ViewBinder bindClickListener(@IdRes int id, @NonNull View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
        return this;
    }

    /**
     * 给多个 {@link View} 绑定一个点击事件
     * @param listener 需要绑定的事件
     * @param ids 需要绑定事件的多个{@link View} id
     * @see View#setOnClickListener(View.OnClickListener)
     */
    @NonNull
    public ViewBinder bindClickListeners(@NonNull View.OnClickListener listener, @IdRes int... ids) {
        ForeachUtils.each(ids, (index, target) -> bindClickListener(target, listener));
        return this;
    }

    /**
     * 绑定OnCheckedChangeListener事件
     * @see CompoundButton#setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener)
     */
    @NonNull
    public ViewBinder bindCheckedChangeListener(@IdRes int id, @NonNull CompoundButton.OnCheckedChangeListener listener) {
        this.<CompoundButton>findViewById(id).setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * 通过 {@link IObservable} 给 {@link View} 添加多个点击事件
     * @see IObservable
     * @see View#setOnClickListener(View.OnClickListener)
     */
    @NonNull
    public ViewBinder addClickListener(@IdRes int id, @NonNull View.OnClickListener clickListener) {
        return addClickListener(findViewById(id), clickListener);
    }

    /**
     * 通过 {@link IObservable} 给 {@link View} 添加多个点击事件
     * @see IObservable
     * @see View#setOnClickListener(View.OnClickListener)
     */
    @NonNull
    public ViewBinder addClickListeners(View.OnClickListener clickListener, @IdRes int... ids) {
        ForeachUtils.each(ids, (index, target) -> addClickListener(target, clickListener));
        return this;
    }

    /**
     * 通过 {@link IObservable} 给 {@link View} 添加多个点击事件
     * {@link IObservable} 对象保存在 {@link R.id#tag_click} 为key的tag上
     * @see IObservable
     * @see View#setOnClickListener(View.OnClickListener)
     */
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

    /**
     * 通过 {@link IObservable} 给 {@link View} 添加多个长按事件
     * {@link IObservable} 对象保存在 {@link R.id#tag_long_click} 为key的tag上
     * @see IObservable
     * @see View#setOnLongClickListener(View.OnLongClickListener)
     */
    @NonNull
    public ViewBinder addLongClickListener(@IdRes int id, @NonNull View.OnLongClickListener longClickListener) {
        return addLongClickListener(findViewById(id), longClickListener);
    }

    /**
     * 通过 {@link IObservable} 给 {@link View} 添加多个长按事件
     * {@link IObservable} 对象保存在 {@link R.id#tag_long_click} 为key的tag上
     * @see IObservable
     * @see View#setOnLongClickListener(View.OnLongClickListener)
     */
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

    /**
     * 通过 {@link IObservable} 给 {@link View} 添加多个长按事件
     * {@link IObservable} 对象保存在 {@link R.id#tag_long_click} 为key的tag上
     * @see IObservable
     * @see View#setOnLongClickListener(View.OnLongClickListener)
     */
    @NonNull
    public ViewBinder addLongClickListeners(@NonNull View.OnLongClickListener longClickListener, int... ids) {
        ForeachUtils.each(ids, (index, target) -> addLongClickListener(target, longClickListener));
        return this;
    }

}
