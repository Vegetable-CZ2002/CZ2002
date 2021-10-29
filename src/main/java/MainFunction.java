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
        MenuItem m = new Food(22, "kfc", MenuItem.Type.MEAT, "i love zkx", 20.0);
        try {
            MenuManager.updateMenuItem(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
