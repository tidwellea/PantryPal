package com.evantidwell.pantrypal.ui;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.evantidwell.pantrypal.R;
import com.evantidwell.pantrypal.database.PantryOpenHelper;
import com.evantidwell.pantrypal.dialog.AddFoodDialogFragment;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static Boolean DEBUG = true;

    TextView pantry, fridge, freezer;

    AddFoodDialogFragment addFoodDialogFragment;
    PantryOpenHelper mPantryOpenHelper = new PantryOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantry = (TextView) findViewById(R.id.pantryLabel);
        fridge = (TextView) findViewById(R.id.fridgeLabel);
        freezer = (TextView) findViewById(R.id.freezerLabel);

        PantryOpenHelper myDb = new PantryOpenHelper(this);

        openPantry();
        openFridge();
        openFreezer();

        if (DEBUG) {
            Log.d(TAG, "MainActivity created");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                if (DEBUG) {Log.d(TAG, "addFoodDialog selected");}
                addFoodDialogFragment= new AddFoodDialogFragment();
                addFoodDialogFragment.show(getFragmentManager(), "add_food_dialog");
                    if (DEBUG) {Log.d(TAG, "addFoodDialog created");}
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void openPantry() {
        pantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PantryFragment fragment = new PantryFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

                if (DEBUG) {
                    Log.d(TAG, "Created PantryFragment.");
                }
            }
        });
    }

    public void openFridge() {
        fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FridgeFragment fragment = new FridgeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

                if (DEBUG) {
                    Log.d(TAG, "Created FridgeFragment.");
                }
            }
        });

    }

    public void openFreezer() {
        freezer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FreezerFragment fragment = new FreezerFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                if (DEBUG) {
                    Log.d(TAG, "Created FreezerFragment.");
                }
            }
        });
    }

}
