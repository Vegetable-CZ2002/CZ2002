package beans;


/**
 * Represents one of the types of menuItem, the ala carte item
 *
 * @author Zhou Yuxuan
 */
public class Food extends MenuItem {

    public Food(int id, String name, Type type, String description, double price) {
        super(id, name, type, description, price);
    }

}
