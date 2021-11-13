package beans;

import java.util.Formatter;
import java.util.List;

/**
 * Represents one of the types of menuitem, the set package item
 *
 * @author Zhou Yuxuan
 */
public class SetPackage extends MenuItem {

    private List<Food> foodList;

    public SetPackage(int id, String name, String description, double price, List<Food> foodList) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", foodList=" + foodList +
                '}';
    }

    @Override
    public Formatter formatter() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Food food : foodList) {
            Formatter fmt = new Formatter();
            fmt.format("%2s %15s", food.getId(), food.getName());
            stringBuilder.append(fmt);
            stringBuilder.append(";  ");
        }
        stringBuilder.append("}");
        Formatter fmt2 = new Formatter();
        return fmt2.format("%2s %28s %8s %10s  %40s  %15s", id, name, price, type, stringBuilder, description);
    }
}
