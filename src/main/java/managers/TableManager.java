package managers;

import beans.Reservation;
import beans.Table;
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

public class TableManager {
    public static List<Table> tables;

    // TODO: 2021/10/28 readTables
    public static List<Table> readTable() throws IOException {
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/table.json");
        String jsonString = Files.readString(file);
        Table[] tableArray = gson.fromJson(jsonString, Table[].class);
        if(tableArray == null){
            tables= new ArrayList<Table>();
        }
        else{
            tables= new ArrayList<Table>(Arrays.asList(tableArray));
        }
        return tables;
    }

    public static void addTable(Table t) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try {
            tables.add(t);
            Table[] tableArray = new Table[tables.size()];
            tables.toArray(tableArray);
            Path file = Path.of("src/main/resources/data/table.json");
            Files.writeString(file, gson.toJson(tableArray), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Table checkTableAvailability(Reservation r){
        for(Table t: tables){
            if(t.isOccupied() == false  && t.getNumOfSeats() >= r.getPax()){
                return t;
            }
        }
        return null;
    }
}
