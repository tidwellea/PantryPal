package com.evantidwell.pantrypal.ui;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.evantidwell.pantrypal.R;
import com.evantidwell.pantrypal.adapters.PantryAdapter;
import com.evantidwell.pantrypal.database.PantryOpenHelper;

import butterknife.ButterKnife;

public class PantryFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);

        ListView mPantryListView = (ListView) view.findViewById(android.R.id.list);

        PantryOpenHelper pantryOpenHelper = new PantryOpenHelper(getActivity());

        PantryAdapter pantryAdapter = new PantryAdapter(getActivity(), pantryOpenHelper.getPantryData(), 0);

        mPantryListView.setAdapter(pantryAdapter);

        return view;
    }
}
