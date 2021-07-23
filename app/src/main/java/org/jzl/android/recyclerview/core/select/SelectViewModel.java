package org.jzl.android.recyclerview.core.select;

import androidx.lifecycle.MutableLiveData;
import org.jzl.android.mvvm.vm.AbstractViewModel;

public class SelectViewModel extends AbstractViewModel<SelectViewHolder> {

    public final MutableLiveData<Integer> icon = new MutableLiveData<>();
    public final MutableLiveData<String> title = new MutableLiveData<>();

}
