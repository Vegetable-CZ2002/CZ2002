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
 *  @author Ruan Donglin
 */
public class TableManager {
    public static List<Table> tables;

    static {
        try {
            tables = readTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all tables from the json file
     *
     * @return all the tables in the restaurant that is in the format of list of Table object
     * @throws IOException
     */
    public static List<Table> readTable() throws IOException {
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/table.json");
        String jsonString = Files.readString(file);
        Table[] tableArray = gson.fromJson(jsonString, Table[].class);
        if(tableArray == null){
            tables= new ArrayList<>();
        }
        else{
            tables= new ArrayList<>(Arrays.asList(tableArray));
        }
        return tables;
    }

    public static void addTable(Table t) {
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

    /**
     * Check all available table to find one that allow the defined number of pax to seat
     *
     * @param pax the number of pax for the order
     * @throws IOException
     */
    public static Table occupyTableForOrder(int pax){
        for(Table t: tables){
            if(!t.isOccupied() && t.getNumOfSeats()>= pax){
                t.setOccupied(true);
                return t;
            }
        }
        System.out.println("No available table for seating");
        return null;
    }


    /**
     * Check all reservation to find the reservation that happens in the current session to set the table as occupied.
     *
     * @throws IOException
     */
    public static void setTableReserved() throws IOException {
        List<Reservation> reservations= ReservationManager.readReservation();
        if(LocalTime.now().isBefore(LocalTime.of(12,00,00))){
            for(Reservation r: reservations){
                if(r.getLocalDate().isEqual(LocalDate.now()) && r.getLocalTime().isBefore(LocalTime.of(12,00,00))){
                    r.getTable().setOccupied(true);
                }
            }
        }
        else{
            for(Reservation r: reservations){
                if(r.getLocalDate().isEqual(LocalDate.now())){
                    r.getTable().setOccupied(true);
                }
            }
        }
    }

}
