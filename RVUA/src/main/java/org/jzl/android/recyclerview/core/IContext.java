package org.jzl.android.recyclerview.core;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.model.IExtractable;
import org.jzl.android.recyclerview.util.ViewBinder;

import java.util.List;

public interface IContext extends IExtractable {

    @NonNull
    View getItemView();

    @NonNull
    IConfiguration<?, ?> getConfiguration();

    @NonNull
    IOptions<?, ?> getOptions();

    @NonNull
    List<Object> getPayloads();

    @NonNull
    ViewBinder getViewBinder();

    int getItemViewType();

    int getLayoutPosition();

    int getAdapterPosition();

    long getItemId();
}
