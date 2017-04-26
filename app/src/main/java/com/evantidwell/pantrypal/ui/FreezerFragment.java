package com.evantidwell.pantrypal.ui;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.evantidwell.pantrypal.R;
import com.evantidwell.pantrypal.adapters.FreezerAdapter;
import com.evantidwell.pantrypal.database.PantryOpenHelper;

import butterknife.ButterKnife;

public class FreezerFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freezer, container, false);

        ListView mFreezerListView = (ListView) view.findViewById(android.R.id.list);

        PantryOpenHelper pantryOpenHelper = new PantryOpenHelper(getActivity());

        FreezerAdapter freezerAdapter = new FreezerAdapter(getActivity(), pantryOpenHelper.getFreezerData(), 0);

        mFreezerListView.setAdapter(freezerAdapter);

        return view;
    }
}
