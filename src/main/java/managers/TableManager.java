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

public class TableManager {
    public static List<Table> tables;

    static {
        try {
            tables = readTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static Table occupyTableForOrder(int pax){
        for(Table t: tables){
            if(t.isOccupied()== false && t.getNumOfSeats()>= pax){
                t.setOccupied(true);
                return t;
            }
        }
        System.out.println("No available table for seating");
        return null;
    }



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


    public static void checkTableAvailability(){

        boolean hasAvailable= false;
        for(Table t: tables){
            if(t.isOccupied()== false){
                hasAvailable= true;
                System.out.println("Table "+ t.getId() + " is available");
            }
        }
        if(!hasAvailable){
            System.out.println("No available table");
        }
    }
}
