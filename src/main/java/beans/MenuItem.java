package beans;

import java.util.Formatter;

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

//    @Override
//    abstract public String toString();

    abstract public Formatter formatter();

}


