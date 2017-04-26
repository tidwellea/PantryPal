package com.evantidwell.pantrypal.adapters;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.evantidwell.pantrypal.R;

public class FreezerAdapter extends CursorAdapter{

    private static final String TAG = FreezerAdapter.class.getSimpleName();

    private Context mContext;

    public FreezerAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View foodItem = inflater.inflate(R.layout.food_item, viewGroup, false);

        FreezerViewHolder viewHolder = new FreezerViewHolder(foodItem);
        foodItem.setTag(viewHolder);

        return foodItem;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        FreezerViewHolder holder = (FreezerViewHolder) view.getTag();

        String foodName = cursor.getString(cursor.getColumnIndexOrThrow("FOOD_NAME"));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("QUANTITY"));

        holder.mFoodLabel.setText(foodName);
        holder.mQuantity.setText(String.valueOf(quantity));
    }

    private class FreezerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mFoodLabel;
        private TextView mQuantity;

        private FreezerViewHolder(View itemView) {
            super(itemView);

            mFoodLabel = (TextView) itemView.findViewById(R.id.foodLabel);
            mQuantity = (TextView) itemView.findViewById(R.id.quantityLabel);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String foodLabel = mFoodLabel.getText().toString();
            String quantityAsString = mQuantity.getText().toString();
            int quantity = Integer.parseInt(quantityAsString);

            String stockedUpMessage = String.format("You have 2+ %s, you should be all set!", foodLabel);
            String shoppingMessage = String.format("You only have 1 %s left," +
                    " you may need to go shopping!", foodLabel);

            if (quantity >= 2) {
                Toast.makeText(mContext, stockedUpMessage, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(mContext, shoppingMessage, Toast.LENGTH_LONG).show();
            }

        }
    }
}
