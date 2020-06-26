package com.ultrainstinct.android.visapay.common.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.ultrainstinct.android.visapay.common.data.AbstractExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableChangeBarcodeDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableSalesDataProvider;

public class ExampleExpandableChangeBarcodeDataProviderFragment extends Fragment {
    private ExampleExpandableChangeBarcodeDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new ExampleExpandableChangeBarcodeDataProvider();
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}

