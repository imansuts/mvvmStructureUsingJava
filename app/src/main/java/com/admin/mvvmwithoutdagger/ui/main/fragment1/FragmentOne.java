package com.admin.mvvmwithoutdagger.ui.main.fragment1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.admin.mvvmwithoutdagger.BR;
import com.admin.mvvmwithoutdagger.R;
import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel;
import com.admin.mvvmwithoutdagger.databinding.FragmentOneBinding;
import com.admin.mvvmwithoutdagger.ui.base.BaseFragment;
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.RecyclerViewAdapter;


public class FragmentOne extends BaseFragment<FragmentOneBinding, FragmentOneViewModel> implements FragmentNavigator {

    private FragmentOneBinding fragmentOneBinding;
    private FragmentOneViewModel fragmentOneViewModel;

    public static FragmentOne newInstance() {
        Bundle args = new Bundle();
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    public FragmentOneViewModel getViewModel() {
        fragmentOneViewModel = ViewModelProviders.of(this).get(FragmentOneViewModel.class);
        return fragmentOneViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentOneViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentOneBinding = getViewDataBinding();

        // Below code is for checking of retrofit, is it working properly with MVVM or not
//        fragmentOneViewModel.fetchUsersList();  // enable it after putting your test base url and api key and test it

        setUpRecycler();

        fragmentOneViewModel.getUserData();

        fragmentOneBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = fragmentOneBinding.etName.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    UserDataModel userDataModel = new UserDataModel();
                    userDataModel.createdAt = String.valueOf(System.currentTimeMillis());
                    userDataModel.name = s;
                    fragmentOneViewModel.saveUserData(userDataModel);


                } else {
                    Toast.makeText(getBaseActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setUpRecycler() {
        RecyclerViewAdapter mOpenSourceAdapter = new RecyclerViewAdapter(getBaseActivity());
        fragmentOneBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        fragmentOneBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentOneBinding.recyclerView.setAdapter(mOpenSourceAdapter);
    }
}
