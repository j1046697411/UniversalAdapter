package org.jzl.android.recyclerview;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jzl.android.mvvm.IView;
import org.jzl.android.mvvm.IViewBindingHelper;
import org.jzl.android.mvvm.IViewBindingHelperFactory;
import org.jzl.android.mvvm.view.core.AbstractMVVMActivity;
import org.jzl.android.mvvm.vm.AbstractViewModel;
import org.jzl.android.recyclerview.core.HomeView;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;

import java.lang.reflect.Constructor;

public class UniversalRecyclerViewActivity extends AbstractMVVMActivity<UniversalRecyclerViewActivity.IUniversalRecyclerView, UniversalRecyclerViewActivity.IUniversalRecyclerViewModel, ActivityRevyclerViewBinding> {

    public static final String TYPE_VIEW = "view";

    @NonNull
    @Override
    public IViewBindingHelper<IUniversalRecyclerView, IUniversalRecyclerViewModel> createViewBindingHelper(IViewBindingHelperFactory factory) {
        return factory.createViewBindingHelper(createUniversalRecyclerView(), R.layout.activity_revycler_view, BR.adapterAnimationViewModel, IUniversalRecyclerViewModel.class);
    }

    public IUniversalRecyclerView createUniversalRecyclerView() {
        try {
            Intent intent = getIntent();
            String viewType = intent.getStringExtra(TYPE_VIEW);
            Class<?> type = Class.forName(viewType);
            Constructor<?> constructor = type.getConstructor(UniversalRecyclerViewActivity.class);
            return (IUniversalRecyclerView) constructor.newInstance(this);
        } catch (Throwable e) {
            return new HomeView(this);
        }
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding dataBinding, IUniversalRecyclerViewModel viewModel) {
        getSelfView().initialize(dataBinding, dataBinding.rvRecyclerView, viewModel);
    }

    public static class IUniversalRecyclerViewModel extends AbstractViewModel<IUniversalRecyclerView> {
        @Override
        protected void preBind(@NonNull IUniversalRecyclerView view) {
            super.preBind(view);
        }
    }

    public interface IUniversalRecyclerView extends IView {

        void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull IUniversalRecyclerViewModel universalRecyclerViewModel);

    }

}
