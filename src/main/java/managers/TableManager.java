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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The manager class that controls the Table class
 *
 * @author Ruan Donglin
 */
public class TableManager extends BaseManager {
    private List<Table> tables;

    public TableManager() throws IOException {
        this.tables = read();
    }


    /**
     * Check all available table to find one that allow the defined number of pax to seat
     *
     * @param pax the number of pax for the order
     */
    public Table occupyTableForOrder(int pax) {
        for (Table t : tables) {
            if (!t.isOccupied() && t.getNumOfSeats() >= pax) {
                t.setOccupied(true);
                return t;
            }
        }
        System.out.println("No available table for seating");
        return null;
    }


    public List<Table> getTables() {
        return tables;
    }

    /**
     * Read all tables from the table.json file
     *
     * @return all the tables in the restaurant that is in the format of list of Table object
     *
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public List read() throws IOException {
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/table.json");
        String jsonString = Files.readString(file);
        Table[] tableArray = gson.fromJson(jsonString, Table[].class);
        if (tableArray == null) {
            tables = new ArrayList<>();
        } else {
            tables = new ArrayList<>(Arrays.asList(tableArray));
        }
        return tables;
    }

    /**
     * Add a table and write it to the table.json file
     *
     * @param o the table object that needs to be added
     */
    public void add(Object o) {
        Table t = (Table) o;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try {
            tables.add(t);
            Table[] tableArray = new Table[tables.size()];
            tables.toArray(tableArray);
            Path file = Path.of("src/main/resources/data/table.json");
            Files.writeString(file, gson.toJson(tableArray), StandardOpenOption.WRITE);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws IOException {

    }

    @Override
    public int getSize() {
        return tables.size();
    }

    @Override
    public void print() {

    }

}
