package com.example.mobaikar.ui.bhavan;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobaikar.R;
import com.example.mobaikar.ui.bhavan.BhavanViewModel;

public class BhavanFragment extends Fragment {

    private BhavanViewModel mViewModel;

    public static BhavanFragment newInstance() {
        return new BhavanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bhavan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BhavanViewModel.class);
        // TODO: Use the ViewModel
    }

}