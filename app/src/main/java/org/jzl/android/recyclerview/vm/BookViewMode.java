package org.jzl.android.recyclerview.vm;

import org.jzl.android.mvvm.IBindVariableOwner;
import org.jzl.android.mvvm.vm.AbstractViewModel;
import org.jzl.android.recyclerview.databinding.LayoutClickBookBinding;
import org.jzl.android.recyclerview.view.ViewHolderHelper;

public class BookViewMode extends AbstractViewModel<ViewHolderHelper<BookViewMode, LayoutClickBookBinding>> implements IBindVariableOwner {

    @Override
    public Object getBindVariableValue() {
        return null;
    }
}
