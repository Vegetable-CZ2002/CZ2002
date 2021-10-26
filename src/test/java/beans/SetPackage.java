package beans;

import java.util.ArrayList;

public class SetPackage extends MenuItem{

    private ArrayList<Food> foodList;


    public SetPackage(String name, String description, double price, ArrayList<Food> foodList) {
        super(name, Type.PACKAGE, description, price);
        this.foodList = foodList;
    }
    

    public ArrayList<Food> getFoodList() {
        return this.foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    
}
