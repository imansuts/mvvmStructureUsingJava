package com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.mvvmwithoutdagger.databinding.SingleRowItemBinding;
import com.admin.mvvmwithoutdagger.databinding.SingleRowItemLoadingBinding;
import com.admin.mvvmwithoutdagger.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Activity activity;
    private static final String TAG = "RecyclerViewAdapter";

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<ListItemsViewModel> listItemsViewModels;

    private OpenSourceAdapterListener mListener;

    public RecyclerViewAdapter(Activity activity) {
        this.activity = activity;
        listItemsViewModels = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (!listItemsViewModels.isEmpty()) {
            return listItemsViewModels.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!listItemsViewModels.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                SingleRowItemBinding openSourceViewBinding = SingleRowItemBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new ItemViewHolder(openSourceViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                SingleRowItemLoadingBinding emptyViewBinding = SingleRowItemLoadingBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new ItemLoadingViewHolder(emptyViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }




    public void clearItems() {
        listItemsViewModels.clear();
    }
    public void addItems(List<ListItemsViewModel> repoList) {
        listItemsViewModels.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface OpenSourceAdapterListener {

        void onRetryClick();
    }
    public void setListener(OpenSourceAdapterListener listener) {
        this.mListener = listener;
    }




    public class ItemViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final SingleRowItemBinding mBinding;
        public ItemViewHolder(SingleRowItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
        @Override
        public void onBind(int position) {
            final ListItemsViewModel mOpenSourceItemViewModel = listItemsViewModels.get(position);
            mBinding.setViewModel(mOpenSourceItemViewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
            mBinding.image.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (listItemsViewModels.get(getAdapterPosition()).data.get() != null) {
                try {
                    Toast.makeText(activity, listItemsViewModels.get(getAdapterPosition()).data.get() , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d(TAG, "onClick_error: "+e.getMessage());
                }
            }
        }
    }


    public class ItemLoadingViewHolder extends BaseViewHolder implements ListItemLoadingViewModel.OpenSourceEmptyItemViewModelListener {
        private final SingleRowItemLoadingBinding mBinding;
        public ItemLoadingViewHolder(SingleRowItemLoadingBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
        @Override
        public void onBind(int position) {
            final ListItemLoadingViewModel mOpenSourceItemViewModel = new ListItemLoadingViewModel(this);
            mBinding.setViewModel(mOpenSourceItemViewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }


        @Override
        public void onRetryClick() {
            Log.d(TAG, "onClick_loading_retry_adapter: "+true);
            mListener.onRetryClick();
        }
    }

}
