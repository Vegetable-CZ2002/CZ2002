package beans;

import java.util.Formatter;
import java.util.Objects;

/**
 * Represents general menuItem
 *
 * @author Zhou Yuxuan
 */
public abstract class MenuItem {

    public enum Type{
        APPETIZER, MEAT, PASTA, PIZZA, DESSERT, DRINK, PACKAGE, OTHER_MAIN_COURSE
    }
    protected int id;
    protected String name;
    protected Type type;
    protected String description;
    protected double price;

    public MenuItem(int id, String name, Type type, String description, double price) {
        this.id= id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public Formatter formatter(){
        Formatter fmt = new Formatter();
        return fmt.format("%2s %28s %8s %10s   %15s", id, name, price, type, description);
    }

}


