package org.jzl.android.recyclerview.core.vh;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IViewHolder;

public class DefaultViewHolder implements IViewHolder {

    private final View itemView;
    private final int itemViewType;

    public DefaultViewHolder(View itemView, int itemViewType) {
        this.itemView = itemView;
        this.itemViewType = itemViewType;
    }

    @NonNull
    public View getItemView() {
        return itemView;
    }

    public int getItemViewType() {
        return itemViewType;
    }
}
