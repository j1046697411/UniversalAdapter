package org.jzl.android.recyclerview.core.header;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IView;
import org.jzl.android.mvvm.IViewBindingHelper;
import org.jzl.android.mvvm.IViewBindingHelperFactory;
import org.jzl.android.recyclerview.BR;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.databinding.DefSectionHeadBinding;
import org.jzl.android.recyclerview.view.AbstractMVVMViewHolder;

public class HeaderViewHolder extends AbstractMVVMViewHolder<IHeaderView, HeaderViewModel, DefSectionHeadBinding> implements IView, IHeaderView {

    public HeaderViewHolder(@NonNull IExtendView<?, ?, ?> parentContainerView, @NonNull View itemView) {
        super(parentContainerView, itemView);
    }

    @NonNull
    @Override
    public IViewBindingHelper<IHeaderView, HeaderViewModel> createViewBindingHelper(IViewBindingHelperFactory factory) {
        return factory.createViewBindingHelper(this, R.layout.def_section_head, BR.headerViewModel, HeaderViewModel.class);
    }

    @Override
    public void initialize(@NonNull DefSectionHeadBinding dataBinding, HeaderViewModel viewModel) {
    }

    @Override
    public void bindHeader(Header header) {
        HeaderViewModel viewModel = getViewModel();
        viewModel.title.setValue(header.getTitle());
        viewModel.description.setValue(header.getDescription());
    }
}
