import adapters.MenuItemAdapter;
import beans.Food;
import beans.MenuItem;
import beans.SetPackage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) throws IOException {

        MenuItem salmon = new Food(2, "Chicken", MenuItem.Type.MAIN_COURSE, "Recommended", 20.0);

        try {
            MenuManager.addMenuItem(salmon);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
