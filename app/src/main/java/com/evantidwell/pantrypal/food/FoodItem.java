package com.evantidwell.pantrypal.food;


public class FoodItem {
    private String mFoodName = null;
    private int mQuantity = -1;

    public FoodItem(String foodName) {
        mFoodName = foodName;
    }

    public String getFoodName() {
        return mFoodName;
    }

    public void setFoodName(String foodName) {
        mFoodName = foodName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }
}
