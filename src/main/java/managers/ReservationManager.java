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
import java.util.*;


/**
 * The manager class that controls the Reservation class
 *
 * @author Ruan Donglin
 */
public class ReservationManager extends BaseManager {
    private List<Reservation> reservations;

    public ReservationManager() throws IOException {
        reservations = read();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    /**
     * Check in to a reservation if allowed
     * The reservation is located according to its reservation id
     *
     * @param id the id of the reservation that the customer wants to check in
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public void reservationCheckIn(int id) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Reservation.class, new MenuItemAdapter());
        boolean removeReservation = false;
        for (Reservation reservation : reservations) {
            {
                if (reservation.getId() == id) {
                    if (reservation.getLocalDate().isAfter(LocalDate.now())) {
                        System.out.println("Not the date for checking reservation yet");
                    } else if (reservation.getLocalTime().isAfter(LocalTime.of(15, 0, 0)) && LocalTime.now().isBefore(LocalTime.of(15, 0, 0))) {
                        System.out.println("Please come after 15 o'clock");
                    } else {
                        removeReservation = true;
                        System.out.println("Check reservation booking success");
                        delete((int) reservation.getId());
                        OrderUI orderUI = new OrderUI();
                        orderUI.createOrderAfterReservation(reservation);
                    }
                    break;
                }
            }
        }
        if (!removeReservation) {
            System.out.println("Check reservation booking failure");
        }
    }

    /**
     * Clear all existing reservations that passed the booking time for 20 minutes
     *
     * @throws IOException Signals that an I/O exception related to the json operation
     */
    public void clearExpiredReservations() throws IOException {
        Iterator<Reservation> reservationIterator = reservations.iterator();
        while (reservationIterator.hasNext()) {
            Reservation r = reservationIterator.next();
            {
                if (LocalDate.now().isAfter(r.getLocalDate())) {
                    reservationIterator.remove();
                    delete((int) r.getId());
                    System.out.println("Reservation " + r.getId() + " expires");
                }
                if (LocalDate.now().isEqual(r.getLocalDate()) && LocalTime.now().isAfter(r.getLocalTime().plusMinutes(30))) {
                    reservationIterator.remove();
                    r.getTable().setOccupied(false);
                    delete((int) r.getId());
                    System.out.println("Reservation " + r.getId() + " expires");
                }
            }
        }
    }

    /**
     * Read all existing reservations from the reservation.json file
     *
     * @return the list of existing reservations in a list of Reservation object
     *
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public List read() throws IOException {
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/reservation.json");
        String jsonString = Files.readString(file);
        Reservation[] reservationArray = gson.fromJson(jsonString, Reservation[].class);
        if (reservationArray == null) {
            reservations = new ArrayList<>();
        } else {
            reservations = new ArrayList<>(Arrays.asList(reservationArray));
        }
        return reservations;
    }


    /**
     * Add a reservation to the reservation list, write it into the json file
     *
     * @param o the reservation that needs to be added
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public void add(Object o) {
        Reservation reservation = (Reservation) o;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try {
            reservations.add(reservation);
            Reservation[] reservationArray = new Reservation[reservations.size()];
            reservations.toArray(reservationArray);
            Path file = Path.of("src/main/resources/data/reservation.json");
            Files.writeString(file, gson.toJson(reservationArray), StandardOpenOption.WRITE);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove the defined reservation from the reservation list
     * Call the deletedReservation method to remove it from the reservation.json file
     *
     * @param id the id of the reservation that needs to be deleted
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public void delete(int id) throws IOException {
        boolean removeReservation = false;
        for (Reservation reservation : reservations) {
            {
                if (reservation.getId() == id) {
                    removeReservation = true;
                    System.out.println("Remove reservation booking success");
                    reservation.getTable().setOccupied(false);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.setPrettyPrinting().create();
                    reservations.remove(reservation);
                    Reservation[] reservations1 = new Reservation[reservations.size()];
                    reservations.toArray(reservations1);
                    Path file = Path.of("src/main/resources/data/reservation.json");
                    Files.delete(file);
                    Files.writeString(file, gson.toJson(reservations1), StandardOpenOption.CREATE_NEW);
                    break;
                }
            }
        }
        if (!removeReservation) {
            System.out.println("Remove reservation booking failure");
        }

    }

    public int getSize() {
        return reservations.size();
    }

    public void print() {
        if (getReservations().size() == 0) {
            System.out.println("No reservation yet");
        } else {
            System.out.println("Here's all the reservations in history");
            Formatter fmt = new Formatter();
            fmt.format("%2s  %10s  %5s  %8s  %3s %12s %15s", "id", "Date", "Time", "tableId", "pax", "name", "contact");
            System.out.println(fmt);
            for (Reservation reservation : getReservations()) {
                System.out.println(reservation.formatter());
            }
        }
    }
}
