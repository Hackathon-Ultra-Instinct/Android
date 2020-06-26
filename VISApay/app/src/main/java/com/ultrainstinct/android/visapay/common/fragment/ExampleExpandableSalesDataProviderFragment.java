package com.ultrainstinct.android.visapay.common.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.ultrainstinct.android.visapay.common.data.AbstractExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableSalesDataProvider;

public class ExampleExpandableSalesDataProviderFragment  extends Fragment {
    private ExampleExpandableSalesDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new ExampleExpandableSalesDataProvider();
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}

