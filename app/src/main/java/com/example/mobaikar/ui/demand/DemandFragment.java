package com.example.mobaikar.ui.demand;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobaikar.R;
import com.example.mobaikar.ui.demand.DemandViewModel;

public class DemandFragment extends Fragment {

    private DemandViewModel mViewModel;

    public static DemandFragment newInstance() {
        return new DemandFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demand, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DemandViewModel.class);
        // TODO: Use the ViewModel
    }

}