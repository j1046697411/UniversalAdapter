package org.jzl.android.recyclerview.core.header;

import androidx.lifecycle.MutableLiveData;

import org.jzl.android.mvvm.vm.AbstractViewModel;
import org.jzl.android.recyclerview.R;

public class HeaderViewModel extends AbstractViewModel<IHeaderView> {

    public final MutableLiveData<String> title = new MutableLiveData<>();
    public final MutableLiveData<String> description = new MutableLiveData<>();

    @Override
    public void initialise() {
        super.initialise();
        description.setValue(view.getApplication().getResources().getString(R.string.more));
    }
}
