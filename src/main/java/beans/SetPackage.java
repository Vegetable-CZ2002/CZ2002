package beans;

import java.util.ArrayList;
import java.util.List;

public class SetPackage extends MenuItem{

    private List<Food> foodList;


    public SetPackage(String name, String description, double price, ArrayList<Food> foodList) {
        super(name, Type.PACKAGE, description, price);
        this.foodList = foodList;
    }
    

    public List<Food> getFoodList() {
        return this.foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    
}
