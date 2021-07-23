package org.jzl.android.recyclerview.core.vh;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IContext;
import org.jzl.android.recyclerview.core.IDataBinder;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;
import org.jzl.android.recyclerview.model.ExtraModel;
import org.jzl.android.recyclerview.model.IExtractable;
import org.jzl.android.recyclerview.util.IViewFinder;
import org.jzl.android.recyclerview.util.ViewBinder;

import java.util.List;

public class ModuleAdapterViewHolder<T, VH extends IViewHolder> extends RecyclerView.ViewHolder implements IContext, IViewHolderOwner<VH>,
        IExtractable, IInitializing {

    @NonNull
    private final IOptions<?, ?> options;
    private final ExtraModel extraModel;
    @NonNull
    private final VH viewHolder;

    private List<Object> payloads;
    private final ViewBinder viewBinder;

    public ModuleAdapterViewHolder(@NonNull IOptions<?, ?> options, @NonNull View itemView, @NonNull VH viewHolder) {
        super(itemView);
        this.extraModel = new ExtraModel();
        this.options = options;
        this.viewHolder = viewHolder;
        this.viewBinder = IViewFinder.of(itemView).binder();
    }

    public void binding(IDataBinder<T, VH> dataBinder, T data, @NonNull List<Object> payloads) {
        this.payloads = payloads;
        dataBinder.binding(this, viewHolder, data);
    }

    @NonNull
    @Override
    public IConfiguration<?, ?> getConfiguration() {
        return options.getConfiguration();
    }

    @NonNull
    @Override
    public IOptions<?, ?> getOptions() {
        return options;
    }

    @NonNull
    @Override
    public List<Object> getPayloads() {
        return payloads;
    }


    @NonNull
    @Override
    public IContext getContext() {
        return this;
    }

    @NonNull
    @Override
    public View getItemView() {
        return itemView;
    }

    @NonNull
    @Override
    public VH getViewHolder() {
        return viewHolder;
    }

    @Override
    public <E> E getExtra(int key) {
        return extraModel.getExtra(key);
    }

    @Override
    public <E> E getExtra(int key, E defValue) {
        return extraModel.getExtra(key, defValue);
    }

    @Override
    public void putExtra(int key, Object value) {
        extraModel.putExtra(key, value);
    }

    @Override
    public boolean hasExtra(int key) {
        return extraModel.hasExtra(key);
    }

    @Override
    public void removeExtra(int key) {
        extraModel.removeExtra(key);
    }

    @NonNull
    public ViewBinder getViewBinder() {
        return viewBinder;
    }

    @Override
    public void initialize() {
        if (viewHolder instanceof IInitializing) {
            ((IInitializing) viewHolder).initialize();
        }
    }
}
