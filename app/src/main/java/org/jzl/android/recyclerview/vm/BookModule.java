package org.jzl.android.recyclerview.vm;

import androidx.annotation.NonNull;

import org.jzl.android.mvvm.IExtendView;
import org.jzl.android.recyclerview.BR;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IOptions;
import org.jzl.android.recyclerview.core.IOptionsBuilder;
import org.jzl.android.recyclerview.databinding.LayoutClickBookBinding;
import org.jzl.android.recyclerview.model.Book;
import org.jzl.android.recyclerview.view.AbstractMVVMModule;
import org.jzl.android.recyclerview.view.ViewHolderHelper;

public class BookModule extends AbstractMVVMModule<Book, BookViewMode, LayoutClickBookBinding> {

    public static final int ITEM_VIEW_TYPE_BOOK = 1;

    public BookModule(IExtendView<?, ?, ?> parentContainerView) {
        super(parentContainerView);
    }

    @NonNull
    @Override
    protected IOptions<Book, ViewHolderHelper<BookViewMode, LayoutClickBookBinding>> setup(IOptionsBuilder<Book, ViewHolderHelper<BookViewMode, LayoutClickBookBinding>> optionsBuilder) {
        return optionsBuilder
                .createItemView(R.layout.layout_click_book, ITEM_VIEW_TYPE_BOOK)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    viewHolder.bindVariable(BR.book, data);
                }, ITEM_VIEW_TYPE_BOOK)
                .build();
    }

    @Override
    protected int getVariableId(int itemViewType) {
        return BR.book;
    }

    @NonNull
    @Override
    protected Class<BookViewMode> getViewModelType(int itemViewType) {
        return BookViewMode.class;
    }

    @Override
    public void initialise(@NonNull ViewHolderHelper<BookViewMode, LayoutClickBookBinding> view, @NonNull LayoutClickBookBinding viewDataBinding, @NonNull BookViewMode viewModel) {
    }
}
