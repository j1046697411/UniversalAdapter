package org.jzl.android.recyclerview.app.core.header;

import android.view.View;

import androidx.annotation.NonNull;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IView;
import org.jzl.android.mvvm.IViewBindingHelper;
import org.jzl.android.mvvm.IViewBindingHelperFactory;
import org.jzl.android.recyclerview.app.BR;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.databinding.DefSectionHeadBinding;
import org.jzl.android.recyclerview.view.AbstractMVVMViewHolder;

public class HeaderFooterViewHolder extends AbstractMVVMViewHolder<IHeaderFooterView, HeaderFooterViewModel, DefSectionHeadBinding> implements IView, IHeaderFooterView {

    public HeaderFooterViewHolder(@NonNull IExtendView<?, ?, ?> parentContainerView, @NonNull View itemView) {
        super(parentContainerView, itemView);
    }

    @NonNull
    @Override
    public IViewBindingHelper<IHeaderFooterView, HeaderFooterViewModel> createViewBindingHelper(IViewBindingHelperFactory factory) {
        return factory.createViewBindingHelper(this, R.layout.def_section_head, BR.headerFooterViewModel, HeaderFooterViewModel.class);
    }

    @Override
    public void initialize(@NonNull DefSectionHeadBinding dataBinding, HeaderFooterViewModel viewModel) {
    }

    @Override
    public void bindHeader(HeaderFooterModel header) {
        HeaderFooterViewModel viewModel = getViewModel();
        viewModel.title.setValue(header.getTitle());
        viewModel.description.setValue(header.getDescription());
    }
}
