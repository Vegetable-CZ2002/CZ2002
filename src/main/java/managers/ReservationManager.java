package managers;

import adapters.MenuItemAdapter;
import beans.Reservation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import console.OrderUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReservationManager {
    public static List<Reservation> reservations;

    public static List<Reservation> readReservation() throws IOException{
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/reservation.json");
        String jsonString = Files.readString(file);
        Reservation[] reservationArray = gson.fromJson(jsonString, Reservation[].class);
        if( reservationArray== null){
            reservations = new ArrayList<Reservation>();
        }
        else{
            reservations = new ArrayList<>(Arrays.asList(reservationArray));
        }
        return reservations;
    }


    public static void addReservation(Reservation reservation) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        reservations= readReservation();
        try {
            reservations.add(reservation);
            Reservation[] reservationArray = new Reservation[reservations.size()];
            reservations.toArray(reservationArray);
            Path file = Path.of("src/main/resources/data/reservation.json");
            Files.writeString(file, gson.toJson(reservationArray), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void removeReservation(int id) throws IOException {
        boolean removeReservation= false;
        for(Reservation reservation: readReservation()){
            {
                if(reservation.getId()== id){
                    removeReservation= true;
                    System.out.println("Remove reservation booking success");
                    reservation.getTable().setOccupied(false);
                    deleteReservation(reservation);
                    break;
                }
            }
        }
        if(!removeReservation){
            System.out.println("Remove reservation booking failure");
        }
    }

    public static void deleteReservation(Reservation reservation) throws IOException {
        reservations= ReservationManager.readReservation();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        reservations.remove(reservation);
        Reservation[] reservations1= new Reservation[reservations.size()];
        reservations.toArray(reservations1);
        Path file = Path.of("src/main/resources/data/reservation.json");
        Files.delete(file);
        Files.writeString(file, gson.toJson(reservations1), StandardOpenOption.CREATE_NEW);
    }



    public static void reservationCheckIn(int id) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Reservation.class, new MenuItemAdapter());
        boolean removeReservation= false;
        for(Reservation reservation: readReservation()){
            {
                if(reservation.getId()== id){
                    if(reservation.getLocalDate().isAfter(LocalDate.now())){
                        System.out.println("Not the date for checking reservation yet");
                    }
                    else if (reservation.getLocalTime().isAfter(LocalTime.of(12,00,00)) &&  LocalTime.now().isBefore(LocalTime.of(12,00,00))){
                        System.out.println("Please come after 12 o'clock");
                    }
                    else{
                        removeReservation= true;
                        System.out.println("Check reservation booking success");
                        deleteReservation(reservation);
                        OrderUI.createOrderAfterReservation(reservation);
                    }
                    break;
                }
            }
        }
        if(!removeReservation){
            System.out.println("Check reservation booking failure");
        }
    }

    public static void clearExpiredReservations() throws IOException {
        Iterator<Reservation> reservationIterator= readReservation().iterator();
        while(reservationIterator.hasNext()){
            Reservation r= reservationIterator.next();
            {
                if(LocalDate.now().isAfter(r.getLocalDate()) ){
                    reservationIterator.remove();
                    deleteReservation(r);
                    System.out.println("Reservation "+ r.getId()+ " expires");
                }
                if(LocalDate.now().isEqual(r.getLocalDate())&&LocalTime.now().isAfter(r.getLocalTime().plusMinutes(30))){
                    reservationIterator.remove();
                    r.getTable().setOccupied(false);
                    deleteReservation(r);
                    System.out.println("Reservation "+ r.getId()+ " expires");
                }
            }
        }
	}


}
