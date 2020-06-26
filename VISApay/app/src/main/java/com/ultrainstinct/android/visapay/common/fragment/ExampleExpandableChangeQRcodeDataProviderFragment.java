package com.ultrainstinct.android.visapay.common.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.ultrainstinct.android.visapay.common.data.AbstractExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableChangeQRcodeDataProvider;

public class ExampleExpandableChangeQRcodeDataProviderFragment extends Fragment {
    private ExampleExpandableChangeQRcodeDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new ExampleExpandableChangeQRcodeDataProvider();
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}

