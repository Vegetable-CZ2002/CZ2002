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


/**
 * The manager class that controls the Reservation class
 *
 * @author Ruan Donglin
 */
public class ReservationManager {
    private List<Reservation> reservations;

    public ReservationManager() throws IOException {
        reservations = readReservation();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * Read all existing reservations from the reservation.json file
     *
     * @return the list of existing reservations in a list of Reservation object
     *
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public List<Reservation> readReservation() throws IOException {
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
     * @param reservation the reservation that needs to be added
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public void addReservation(Reservation reservation) {
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
    public void removeReservation(int id) throws IOException {
        boolean removeReservation = false;
        for (Reservation reservation : reservations) {
            {
                if (reservation.getId() == id) {
                    removeReservation = true;
                    System.out.println("Remove reservation booking success");
                    reservation.getTable().setOccupied(false);
                    deleteReservation(reservation);
                    break;
                }
            }
        }
        if (!removeReservation) {
            System.out.println("Remove reservation booking failure");
        }
    }


    /**
     * Remove a reservation from the reservation.json file
     *
     * @param reservation the reservation that needs to be deleted
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public void deleteReservation(Reservation reservation) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        reservations.remove(reservation);
        Reservation[] reservations1 = new Reservation[reservations.size()];
        reservations.toArray(reservations1);
        Path file = Path.of("src/main/resources/data/reservation.json");
        Files.delete(file);
        Files.writeString(file, gson.toJson(reservations1), StandardOpenOption.CREATE_NEW);
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
                        deleteReservation(reservation);
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
                    deleteReservation(r);
                    System.out.println("Reservation " + r.getId() + " expires");
                }
                if (LocalDate.now().isEqual(r.getLocalDate()) && LocalTime.now().isAfter(r.getLocalTime().plusMinutes(30))) {
                    reservationIterator.remove();
                    r.getTable().setOccupied(false);
                    deleteReservation(r);
                    System.out.println("Reservation " + r.getId() + " expires");
                }
            }
        }
    }


}
