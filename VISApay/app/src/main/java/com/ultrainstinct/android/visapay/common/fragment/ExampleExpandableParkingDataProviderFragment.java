package com.ultrainstinct.android.visapay.common.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.ultrainstinct.android.visapay.common.data.AbstractExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableParkingDataProvider;

public class ExampleExpandableParkingDataProviderFragment extends Fragment {
    private ExampleExpandableParkingDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new ExampleExpandableParkingDataProvider();
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}


