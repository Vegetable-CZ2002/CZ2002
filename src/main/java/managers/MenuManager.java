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


/**
 * The manager class that controls the MenuItem class
 *
 *  @author Zhou Yuxuan
 */
public class MenuManager{

    private List<MenuItem> menuItemList;

    public MenuManager() throws IOException {
        menuItemList= readMenuItem();
    }



    public List<MenuItem> readMenuItem() throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.create();
        Path file = Path.of("src/main/resources/data/menu.json");
        String jsonString = Files.readString(file);
        MenuItem[] foodArray = gson.fromJson(jsonString, MenuItem[].class);
        if(foodArray == null){
            menuItemList = new ArrayList<>();
        }
        else{
            menuItemList = new ArrayList<>(Arrays.asList(foodArray));
        }
        return menuItemList;
    }

    public void addMenuItem(MenuItem m) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            int i = 0;
            for(; i < menuItemList.size(); i++){
                if(m.getType().ordinal() < menuItemList.get(i).getType().ordinal()){
                    menuItemList.add(i, m);
                    break;
                }
            }
            if(i == menuItemList.size()){
                menuItemList.add(m);
            }
            for(; i < menuItemList.size(); i++){
                menuItemList.get(i).setId(i + 1);
            }
            MenuItem[] menuItems = new MenuItem[menuItemList.size()];
            menuItemList.toArray(menuItems);
            Path file = Path.of("src/main/resources/data/menu.json");
            Files.delete(file);
            Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.CREATE_NEW);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteMenuItem(int id) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            if(id - 1 < menuItemList.size()){
                menuItemList.remove(id - 1);
                for(int i = id - 1; i < menuItemList.size(); i++){
                    menuItemList.get(i).setId(i + 1);
                }
                MenuItem[] menuItems = new MenuItem[menuItemList.size()];
                menuItemList.toArray(menuItems);
                Path file = Path.of("src/main/resources/data/menu.json");
                Files.delete(file);
                Files.writeString(file, gson.toJson(menuItems), StandardOpenOption.CREATE_NEW);
            }
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMenuItem(MenuItem m) throws IOException{
        deleteMenuItem(m.getId());
        addMenuItem(m);
    }

    public void printMenu() throws IOException {
        Formatter fmt = new Formatter();
        fmt.format("%2s %28s %8s %10s   %15s", "id", "name", "price", "type", "description");
        System.out.println(fmt);
        for(MenuItem item: menuItemList){
            System.out.println(item.formatter());
        }
    }

    public int menuSize() throws IOException {
        List<MenuItem> menuItems = readMenuItem();
        return menuItems.size();
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
