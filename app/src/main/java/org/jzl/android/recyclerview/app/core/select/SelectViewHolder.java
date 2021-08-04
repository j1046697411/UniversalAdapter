package org.jzl.android.recyclerview.app.core.select;

import android.view.View;
import androidx.annotation.NonNull;
import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.mvvm.IViewBindingHelper;
import org.jzl.android.mvvm.IViewBindingHelperFactory;
import org.jzl.android.recyclerview.app.BR;
import org.jzl.android.recyclerview.app.R;
import org.jzl.android.recyclerview.app.databinding.ItemSectionContentBinding;
import org.jzl.android.recyclerview.app.model.Card;
import org.jzl.android.recyclerview.view.AbstractMVVMViewHolder;

public class SelectViewHolder extends AbstractMVVMViewHolder<SelectViewHolder, SelectViewModel, ItemSectionContentBinding> {

    public SelectViewHolder(@NonNull IExtendView<?, ?, ?> parentContainerView, @NonNull View itemView) {
        super(parentContainerView, itemView);
    }

    @NonNull
    @Override
    public IViewBindingHelper<SelectViewHolder, SelectViewModel> createViewBindingHelper(IViewBindingHelperFactory factory) {
        return factory.createViewBindingHelper(this, R.layout.item_section_content, BR.selectViewModel, SelectViewModel.class);
    }

    @Override
    public void initialize(@NonNull ItemSectionContentBinding dataBinding, SelectViewModel viewModel) {
    }

    public void bindCard(Card card) {
        SelectViewModel selectViewModel = getViewModel();
        selectViewModel.title.setValue(card.getTitle());
        selectViewModel.icon.setValue(card.getIcon());
    }
}
