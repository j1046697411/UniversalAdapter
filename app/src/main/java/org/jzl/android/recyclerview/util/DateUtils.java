package org.jzl.android.recyclerview.util;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import org.jzl.lang.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    @SuppressLint("ConstantLocale")
    public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @BindingAdapter("bindDate")
    public static void format(TextView textView, Date date) {
        if (ObjectUtils.isNull(date)) {
            return;
        }
        textView.setText(yyyy_MM_dd.format(date));
    }

    @BindingAdapter("bindImagerResId")
    public static void bindImageResource(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

    public static String format(Date date){
        return yyyy_MM_dd.format(date);
    }
}
