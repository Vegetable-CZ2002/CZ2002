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

        Food food = new Food(2, "Chicken", MenuItem.Type.MEAT, "Recommended", 20.0);
        List<Food> foodList= new ArrayList<>();
        foodList.add(food);
        foodList.add(food);
        MenuItem menuItem= new SetPackage(30, "aaa", "nonono", 30.0, foodList);

        try {
            MenuManager.addMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
