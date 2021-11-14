package beans;


import java.util.Formatter;

/**
 * Represents one of the types of menuItem, the ala carte item
 *
 * @author Zhou Yuxuan
 */
public class Food extends MenuItem {

    public Food(int id, String name, Type type, String description, double price) {
        super(id, name, type, description, price);
    }

    public Formatter formatter() {
        Formatter fmt = new Formatter();
        return fmt.format("%2s %28s %8s %10s   %15s", id, name, price, type, description);
    }
}
