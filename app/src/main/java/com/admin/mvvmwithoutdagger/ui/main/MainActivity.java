package com.admin.mvvmwithoutdagger.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.admin.mvvmwithoutdagger.BR;
import com.admin.mvvmwithoutdagger.R;
import com.admin.mvvmwithoutdagger.databinding.ActivityMainBinding;
import com.admin.mvvmwithoutdagger.ui.base.BaseActivity;
import com.admin.mvvmwithoutdagger.ui.main.fragment1.FragmentOne;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator{

    private MainViewModel mainViewModel;

    private ActivityMainBinding activityMainBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel.setNavigator(this);
        activityMainBinding = getViewDataBinding();
    }

    @Override
    public void onClick() {
        Toast.makeText(this, "Test Click", Toast.LENGTH_SHORT).show();
        checkFragmentInBackstackAndOpen(FragmentOne.newInstance(), activityMainBinding.frameLayout.getId());
    }
}
