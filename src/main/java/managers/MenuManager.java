package managers;

import adapters.MenuItemAdapter;
import beans.MenuItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

public class MenuManager{

    public static List<MenuItem> menuItemList;

    public static List<MenuItem> readMenuItem() throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.create();
        Path file = Path.of("src/main/resources/data/menu.json");
        String jsonString = Files.readString(file);
        MenuItem[] foodArray = gson.fromJson(jsonString, MenuItem[].class);
        if(foodArray == null){
            menuItemList = new ArrayList<MenuItem>();
        }
        else{
            menuItemList = new ArrayList<MenuItem>(Arrays.asList(foodArray));
        }
        return menuItemList;
    }


    public static void addMenuItem(MenuItem m) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
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
            for(int i = 0; i < menuItemList.size(); i++){
                if(menuItemList.get(i).getId() == id){
                    menuItemList.remove(i);
                    MenuItem[] menuItems = new MenuItem[menuItemList.size()];
                    menuItemList.toArray(menuItems);
                    Path file = Path.of("src/main/resources/data/menu.json");
                    Files.delete(file);
                    Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.CREATE_NEW);
                    break;
                }
            }
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
            for(int i = 0; i < menuItemList.size(); i++){
                if(menuItemList.get(i).getId() == m.getId()){
                    menuItemList.remove(i);
                    menuItemList.add(m);
                    MenuItem[] menuItems = new MenuItem[menuItemList.size()];
                    menuItemList.toArray(menuItems);
                    Path file = Path.of("src/main/resources/data/menu.json");
                    Files.delete(file);
                    Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.CREATE_NEW);
                    break;
                }
            }
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() throws IOException {
        List<MenuItem> menuItems = MenuManager.readMenuItem();
        Formatter fmt = new Formatter();
        fmt.format("%2s %28s %8s %10s   %15s", "id", "name", "price", "type", "description");
        System.out.println(fmt);
        for(MenuItem item: menuItems){
            System.out.println(item.formatter());
        }
    }

    public static int menuSize() throws IOException {
        List<MenuItem> menuItems = MenuManager.readMenuItem();
        return menuItems.size();
    }

}
