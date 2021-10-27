package managers;

import adapters.MenuItemAdapter;
import beans.MenuItem;
import beans.Order;
import beans.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuManager{

    static ArrayList<MenuItem> menu= Restaurant.menuItems;

    public static List<MenuItem> readMenuItem() throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.create();

        Path path = new File("cz2002/src/main/java/cz2002/data/menu.json").toPath();
        
        try (Reader reader = Files.newBufferedReader(path,
        StandardCharsets.UTF_8)) {

            MenuItem[] foodArray = gson.fromJson(reader, MenuItem[].class);
            ArrayList<MenuItem> menuItems= (ArrayList<MenuItem>) Arrays.asList(foodArray);
            return menuItems;
        }
    }


    public void addMenuItem(MenuItem m) throws IOException{
        Gson gson = new Gson();
        menu.add(m);
        try {
            FileWriter writer = new FileWriter("cz2002/src/main/java/cz2002/data/menu.json");
            writer.write(gson.toJson(menu));
            writer.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2021/10/28 delete menuItem
    //input MenuItem m

    // TODO: 2021/10/28 update menuItem:
    //input: MenuItem m whose id is already in the menu.
    //could delete old one in menu, then add the new one into the menu.

}
