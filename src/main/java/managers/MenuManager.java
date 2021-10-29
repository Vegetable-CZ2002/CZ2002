package managers;

import adapters.MenuItemAdapter;
import beans.MenuItem;
import beans.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuManager{
    public static List<MenuItem> readMenuItem() throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.create();
        Path file = Path.of("src/main/resources/data/menu.json");
        String jsonString = Files.readString(file);
        MenuItem[] foodArray = gson.fromJson(jsonString, MenuItem[].class);
        if(foodArray == null){
            return new ArrayList<MenuItem>();
        }
        else{
            List<MenuItem> menuItems= Arrays.asList(foodArray);
            return menuItems;
        }
    }


    public static void addMenuItem(MenuItem m) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            List<MenuItem> menuItemList = new ArrayList<>(readMenuItem());
            menuItemList.add(m);
            MenuItem[] menuItems = new MenuItem[menuItemList.size()];
            menuItemList.toArray(menuItems);
            Path file = Path.of("src/main/resources/data/menu.json");
            Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMenuItem(long id) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            List<MenuItem> menuItemList = new ArrayList<>(readMenuItem());
            for(int i = 0; i < menuItemList.size(); i++){
                if(menuItemList.get(i).getId() == id){
                    menuItemList.remove(i);
                    break;
                }
            }
            MenuItem[] menuItems = new MenuItem[menuItemList.size()];
            menuItemList.toArray(menuItems);
            Path file = Path.of("src/main/resources/data/menu.json");
            Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateMenuItem(MenuItem m) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            List<MenuItem> menuItemList = new ArrayList<>(readMenuItem());
            for(int i = 0; i < menuItemList.size(); i++){
                if(menuItemList.get(i).getId() == m.getId()){
                    menuItemList.remove(i);
                    break;
                }
            }
            menuItemList.add(m);
            MenuItem[] menuItems = new MenuItem[menuItemList.size()];
            menuItemList.toArray(menuItems);
            Path file = Path.of("src/main/resources/data/menu.json");
            Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() throws IOException {
        List<MenuItem> menuItems = MenuManager.readMenuItem();
        for(MenuItem item: menuItems){
            System.out.printf(item.toString()+"\n");
        }
    }

    public static int menuSize() throws IOException {
        List<MenuItem> menuItems = MenuManager.readMenuItem();
        return menuItems.size();
    }

}
