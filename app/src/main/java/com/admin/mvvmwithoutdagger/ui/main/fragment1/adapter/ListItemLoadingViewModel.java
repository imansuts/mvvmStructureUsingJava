package com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter;

public class ListItemLoadingViewModel {

    private final OpenSourceEmptyItemViewModelListener mListener;

    public ListItemLoadingViewModel(OpenSourceEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface OpenSourceEmptyItemViewModelListener {

        void onRetryClick();
    }

}
