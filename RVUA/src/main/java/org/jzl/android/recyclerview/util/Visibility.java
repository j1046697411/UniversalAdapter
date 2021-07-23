package org.jzl.android.recyclerview.util;

import android.view.View;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE_PARAMETER, ElementType.FIELD, ElementType.PARAMETER})
@IntDef(value = {View.GONE, View.VISIBLE, View.INVISIBLE})
public @interface Visibility {
}
