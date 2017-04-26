package com.evantidwell.pantrypal.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.evantidwell.pantrypal.R;
import com.evantidwell.pantrypal.database.PantryOpenHelper;

public class AddFoodDialogFragment extends DialogFragment {

    PantryOpenHelper mPantryOpenHelper;

    Spinner dropdown;
    EditText foodNameEditText;
    EditText quantityEditText;

    String[] locations = new String []{"Pantry", "Fridge", "Freezer"};

    public AddFoodDialogFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mPantryOpenHelper = new PantryOpenHelper(getActivity());

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_food, null);
        builder.setView(view);

        dropdown = (Spinner) view.findViewById(R.id.add_dialog_location_spinner);
        foodNameEditText = (EditText) view.findViewById(R.id.add_dialog_foodname);
        quantityEditText = (EditText) view.findViewById(R.id.add_dialog_quantity);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, locations);
        dropdown.setAdapter(adapter);

        builder
        .setPositiveButton("Stock up!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean isInserted = mPantryOpenHelper
                        .insertData(
                                foodNameEditText.getText().toString(),
                                Integer.parseInt(quantityEditText.getText().toString()),
                                dropdown.getSelectedItem().toString());
                if (isInserted) {
                    Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error During Data Insertion", Toast.LENGTH_SHORT).show();
                }

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

       return builder.create();
    }
}
