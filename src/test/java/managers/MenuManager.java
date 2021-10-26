package managers;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.util.Arrays;
import java.nio.file.Path;
import java.util.List;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonIOException;

import beans.MenuItem;

public class MenuManager{

    public MenuItem[] readMenuItem() throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.create();

        Path path = new File("cz2002/src/main/java/cz2002/data/menu.json").toPath();
        
        try (Reader reader = Files.newBufferedReader(path,
        StandardCharsets.UTF_8)) {

            MenuItem[] foodArray = gson.fromJson(reader, MenuItem[].class);

            return foodArray;
        }
    }


    public void saveMenuItem(MenuItem m) throws IOException{
        Gson gson = new Gson();
        List<MenuItem> menu = Arrays.asList(readMenuItem());
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
}
