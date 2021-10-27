package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetPackage extends MenuItem{

    private List<Food> foodList;


    public SetPackage(long id, String name, String description, double price, List<Food> foodList) {
        super(id, name, Type.PACKAGE, description, price);
        this.foodList = foodList;
    }
    

    public List<Food> getFoodList() {
        return this.foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "SetPackage{" +
                "foodList=" + foodList +
                '}';
    }
}
