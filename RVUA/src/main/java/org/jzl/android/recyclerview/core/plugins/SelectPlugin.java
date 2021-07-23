package org.jzl.android.recyclerview.core.plugins;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.recyclerview.core.IAdapterObservable;
import org.jzl.android.recyclerview.core.IBindPolicy;
import org.jzl.android.recyclerview.core.IConfiguration;
import org.jzl.android.recyclerview.core.IConfigurationBuilder;
import org.jzl.android.recyclerview.core.IDataBinder;
import org.jzl.android.recyclerview.core.IDataGetter;
import org.jzl.android.recyclerview.core.IMatchPolicy;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IPlugin;
import org.jzl.android.recyclerview.core.IViewHolder;
import org.jzl.android.recyclerview.core.IViewHolderOwner;
import org.jzl.android.recyclerview.core.components.IComponent;
import org.jzl.android.recyclerview.model.ISelectable;
import org.jzl.lang.util.ArrayUtils;
import org.jzl.lang.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public final class SelectPlugin<T extends ISelectable, VH extends IViewHolder> implements
        IPlugin<T, VH>, IComponent<T, VH>, ISelector<T> {

    public static final String PAYLOAD = "select";

    private SelectMode selectMode;
    private final IMatchPolicy matchPolicy;
    private final ISelectInterceptor<T, VH> selectInterceptor;
    private final IDataBinder<T, VH> dataBinder;

    private List<T> dataProvider;
    private IDataGetter<T> dataGetter;
    private IAdapterObservable<T, VH> adapterObservable;

    private SelectPlugin(SelectMode selectMode, ISelectInterceptor<T, VH> selectInterceptor, IDataBinder<T, VH> dataBinder, IMatchPolicy matchPolicy) {
        this.selectMode = ObjectUtils.get(selectMode, SelectMode.SINGLE);
        this.matchPolicy = matchPolicy;
        this.selectInterceptor = selectInterceptor;
        this.dataBinder = dataBinder;
    }

    @Override
    public void setup(@NonNull IConfigurationBuilder<T, VH> builder) {
        builder.addCreatedViewHolderListener((options, viewHolderOwner) -> selectInterceptor.intercept(options, viewHolderOwner, this), matchPolicy)
                .dataBinding(dataBinder, IBindPolicy.BIND_POLICY_NOT_INCLUDED_PAYLOADS.or(IBindPolicy.ofPayloads(PAYLOAD)).and(matchPolicy.toBindPolicy()))
                .addComponent(this);
    }

    @Override
    public void initialize(IConfiguration<T, VH> configuration) {
        this.dataProvider = configuration.getDataProvider();
        this.dataGetter = configuration.getDataGetter();
        this.adapterObservable = configuration.getAdapterObservable();
    }

    public void setSelectMode(@NonNull SelectMode selectMode) {
        this.selectMode = ObjectUtils.get(selectMode, this.selectMode);
    }

    @Override
    public void checked(int position, boolean checked) {
        if (selectMode == SelectMode.SINGLE) {
            uncheckedAll();
        }
        T data = dataGetter.getData(position);
        if (ObjectUtils.nonNull(data)) {
            data.checked(checked);
            adapterObservable.notifyItemRangeChanged(position, 1, PAYLOAD);
        }
    }

    @Override
    public void checked(T data, boolean checked) {
        int position = dataProvider.indexOf(data);
        if (position >= 0) {
            checked(position, checked);
        }
    }

    @Override
    public void checkedAll() {
        if (selectMode == SelectMode.SINGLE) {
            return;
        }
        if (ObjectUtils.nonNull(dataProvider)) {
            int startPosition = -1;
            int count = 0;
            for (int i = 0, size = dataProvider.size(); i < size; i++) {
                T data = dataProvider.get(i);
                if (ObjectUtils.nonNull(data) && !data.isChecked()) {
                    data.checked(true);
                    if (count == 0) {
                        startPosition = i;
                    }
                    count++;
                } else if (startPosition != -1) {
                    notifyItemRangeChanged(startPosition, count);
                    startPosition = -1;
                    count = 0;
                }
            }
            notifyItemRangeChanged(startPosition, count);
        }
    }

    @NonNull
    public final List<T> getSelectResult() {
        List<T> result = new ArrayList<>();
        for (T t : dataProvider) {
            if (t.isChecked()) {
                result.add(t);
            }
        }
        return result;
    }

    private void notifyItemRangeChanged(int startPosition, int itemCount) {
        if (startPosition >= 0 && itemCount >= 1) {
            adapterObservable.notifyItemRangeChanged(startPosition, itemCount, PAYLOAD);
        }
    }

    @Override
    public void uncheckedAll() {
        if (ObjectUtils.nonNull(dataProvider)) {
            int startPosition = -1;
            int count = 0;
            for (int i = 0, size = dataProvider.size(); i < size; i++) {
                T data = dataProvider.get(i);
                if (ObjectUtils.nonNull(data) && data.isChecked()) {
                    data.checked(false);
                    if (count == 0) {
                        startPosition = i;
                    }
                    count++;
                } else if (startPosition != -1) {
                    notifyItemRangeChanged(startPosition, count);
                    startPosition = -1;
                    count = 0;
                }
            }
            notifyItemRangeChanged(startPosition, count);
        }
    }


    @NonNull
    public static <T extends ISelectable, VH extends IViewHolder> SelectPlugin<T, VH> of(@NonNull SelectMode selectMode, @NonNull ISelectInterceptor<T, VH> selectInterceptor, @NonNull IDataBinder<T, VH> dataBinder, @NonNull IMatchPolicy matchPolicy) {
        return new SelectPlugin<>(selectMode, selectInterceptor, dataBinder, matchPolicy);
    }

    @NonNull
    public static <T extends ISelectable, VH extends IViewHolder> SelectPlugin<T, VH> of(@NonNull SelectMode selectMode, @NonNull IDataBinder<T, VH> dataBinder, @NonNull IMatchPolicy matchPolicy, @NonNull int... ids) {
        return of(selectMode, new DefaultSelectInterceptor<>(ids), dataBinder, matchPolicy);
    }

    public enum SelectMode {
        SINGLE, MULTIPLE
    }

    private static class DefaultSelectInterceptor<T extends ISelectable, VH extends IViewHolder> implements ISelectInterceptor<T, VH> {

        private final int[] ids;

        public DefaultSelectInterceptor(int... ids) {
            this.ids = ids;
        }

        @Override
        public void intercept(IOptions<T, VH> options, @NonNull IViewHolderOwner<VH> viewHolderOwner, ISelector<T> selector) {
            if (ArrayUtils.nonEmpty(ids)) {
                viewHolderOwner.getViewBinder().addClickListeners(v -> {
                    IDataGetter<T> dataGetter = options.getDataGetter();
                    T data = dataGetter.getData(viewHolderOwner.getContext().getAdapterPosition());
                    checked(selector, data, viewHolderOwner.getContext().getAdapterPosition());
                }, ids);
            } else {
                viewHolderOwner.getViewBinder().addClickListener(viewHolderOwner.getItemView(), v -> {
                    IDataGetter<T> dataGetter = options.getDataGetter();
                    T data = dataGetter.getData(viewHolderOwner.getContext().getAdapterPosition());
                    checked(selector, data, viewHolderOwner.getContext().getAdapterPosition());
                });
            }
        }

        private void checked(ISelector<T> selector, T data, int position) {
            selector.checked(position, !data.isChecked());
        }
    }

}
