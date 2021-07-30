package org.jzl.android.recyclerview.core;

import android.os.Handler;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.recyclerview.core.components.IComponentManager;
import org.jzl.android.recyclerview.core.layout.ILayoutManagerFactory;
import org.jzl.android.recyclerview.core.layout.IRecyclerViewLayoutManager;
import org.jzl.android.recyclerview.core.layout.ISpanSizeLookup;
import org.jzl.android.recyclerview.core.listeners.IListenerManager;
import org.jzl.android.recyclerview.core.listeners.IListenerManagerBuilder;
import org.jzl.android.recyclerview.core.listeners.ListenerManager;
import org.jzl.android.recyclerview.core.module.IAdapterModule;
import org.jzl.android.recyclerview.core.module.IModule;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface IConfiguration<T, VH extends IViewHolder> extends IDataGetterOwner<T>, IListenerManagerBuilder<T, VH, IConfiguration<T, VH>> {

    @NonNull
    static <T, VH extends IViewHolder> IConfigurationBuilder<T, VH> builder(@NonNull IViewHolderFactory<VH> viewHolderFactory) {
        return Configuration.builder(viewHolderFactory);
    }

    @NonNull
    static <T> IConfigurationBuilder<T, DefaultViewHolder> builder(){
        return builder(IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY);
    }

    @NonNull
    default <T1, VH1 extends IViewHolder> IOptionsBuilder<T1, VH1> options(@NonNull IModule<T1, VH1> module, @NonNull IViewHolderFactory<VH1> viewHolderFactory, @NonNull IDataGetter<T1> dataGetter, @NonNull IListenerManager<T1, VH1> listenerManager) {
        return Options.builder(this, module, viewHolderFactory, dataGetter, listenerManager);
    }

    @NonNull
    default <T1, VH1 extends IViewHolder> IOptionsBuilder<T1, VH1> options(@NonNull IModule<T1, VH1> module, @NonNull IViewHolderFactory<VH1> viewHolderFactory, @NonNull IDataGetter<T1> dataGetter) {
        return options(module, viewHolderFactory, dataGetter, new ListenerManager<>());
    }

    @NonNull
    default IConfiguration<T, VH> layoutManager(ILayoutManagerFactory<T, VH> layoutManagerFactory) {
        getRecyclerViewLayoutManager().layoutManager(layoutManagerFactory);
        return this;
    }

    @NonNull
    default IConfiguration<T, VH> setSpanSizeLookup(@NonNull ISpanSizeLookup<T, VH> spanSizeLookup) {
        getRecyclerViewLayoutManager().setSpanSizeLookup(spanSizeLookup);
        return this;
    }

    @NonNull
    IAdapterModule<T, VH> getAdapterModule();

    @NonNull
    List<T> getDataProvider();

    @NonNull
    IDataClassifier<T, VH> getDataClassifier();

    @NonNull
    IIdentityProvider<T, VH> getIdentityProvider();

    @NonNull
    LayoutInflater getLayoutInflater();

    @NonNull
    @Override
    IDataGetter<T> getDataGetter();

    @NonNull
    IOptions<T, VH> getOptions();

    @NonNull
    RecyclerView.Adapter<?> getAdapter();

    @NonNull
    IComponentManager<T, VH> getComponentManager();

    @NonNull
    IRecyclerViewLayoutManager<T, VH> getRecyclerViewLayoutManager();

    @NonNull
    IAdapterObservable<T, VH> getAdapterObservable();

    boolean isDataEmpty();

    @NonNull
    Handler getMainHandler();

    @NonNull
    ExecutorService getExecutorService();

    void rebind(@NonNull RecyclerView recyclerView);
}
