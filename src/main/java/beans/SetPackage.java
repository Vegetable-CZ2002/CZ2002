package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetPackage extends MenuItem{

    private List<Food> foodList;
    private long id_;
    private String name_;
    private Type type_;
    private String description_;
    private double price_;

    public SetPackage(long id, String name, String description, double price, List<Food> foodList) {
        super(id, name, Type.PACKAGE, description, price);
        this.name_= name;
        this.id_= id;
        this.description_= description;
        this.price_= price;
        this.type_= Type.PACKAGE;
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
                "id=" + id_ +
                ", name='" + name_ + '\'' +
                ", type=" + type_ +
                ", description='" + description_ + '\'' +
                ", price=" + price_ +
                ", foodList=" + foodList +
                '}';
    }
}
