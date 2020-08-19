package com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter;

import androidx.databinding.ObservableField;

public class ListItemsViewModel {

    public final ObservableField<String> data = new ObservableField<>();

    public ListItemsViewModel(String data) {
        /*if (data==null || TextUtils.isEmpty(data)){
            data = "Default Text";
        }*/
        this.data.set(data);
    }
}
