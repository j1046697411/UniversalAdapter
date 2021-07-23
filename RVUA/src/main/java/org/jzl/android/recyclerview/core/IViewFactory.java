package org.jzl.android.recyclerview.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

public interface IViewFactory {

    static IViewFactory of(@LayoutRes int layoutResId) {
        return (layoutInflater, parent, viewType) -> layoutInflater.inflate(layoutResId, parent, false);
    }

    View create(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

}
