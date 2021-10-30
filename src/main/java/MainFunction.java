import beans.Food;
import beans.MenuItem;
import managers.MenuManager;

import java.io.IOException;

public class MainFunction {

    public static void main(String[] args) {
        try {
            MenuManager.readMenuItem();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuItem m = new Food(23, "kfc", MenuItem.Type.MEAT, "12345", 20.0);
        try {
            MenuManager.updateMenuItem(m);
            m.setDescription("67890");
            MenuManager.updateMenuItem(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
