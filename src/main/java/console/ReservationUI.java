package console;

import beans.Reservation;
import beans.Table;
import managers.ReservationManager;
import managers.TableManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * User interface class for the instructions regarding reservation
 *
 * @author Ruan Donglin
 */
public class ReservationUI {
    private final ReservationManager reservationManager;

    public ReservationUI() throws IOException {
        this.reservationManager = new ReservationManager();
    }

    //private static Scanner in= new Scanner(System.in);
    private static final Scanner in = MainUI.in;

    /**
     * View all existing reservations
     * It calls the getReservations method in the reservationManager class
     *
     * @throws IOException Signals that an I/O exception occurs in the reservationManager related to json operation
     */
    void print() throws IOException {
        reservationManager.clearExpiredReservations();
        reservationManager.print();
    }

    public void mainUI() throws IOException {
        int num;
        do {
            System.out.println("Welcome to the reservation section! What action do you wish to take?");
            System.out.println("[0] Exit from reservation section");
            System.out.println("[1] Create reservation booking");
            System.out.println("[2] Check reservation booking");
            System.out.println("[3] Remove reservation booking");
            System.out.println("[4] View reservation booking");
            num = in.nextInt();
            switch (num) {
                case 1:
                    createReservation();
                    break;
                case 2:
                    checkReservation();
                    break;
                case 3:
                    removeReservation();
                    break;
                case 4:
                    print();
                    break;
                default:
            }
        } while (num != 0);
    }


    /**
     * Create a reservation if allowed with all the needed information of the new reservation
     * Fail if no table is available for that reservation time
     * Return a reservation id for the customer to enter when they check in/ delete
     *
     * @throws IOException Signals that an I/O exception occurs in the reservationManager related to json operation
     */
    public void createReservation() throws IOException {
        reservationManager.clearExpiredReservations();
        System.out.println("Please enter the following details for reservation ");
        System.out.println("Please enter the reservation date in the format of YYYY-MM-DD:(eg. 2021-11-12)");
        in.nextLine();
        String date = in.nextLine();
        LocalDate localDate = LocalDate.parse(date);
        System.out.println(date);
        System.out.println("Please enter the reservation time in the format of HH:MM(eg. 18:00)");
        String time = in.nextLine();
        LocalTime localTime = LocalTime.parse(time + ":00");
        if (localDate.isBefore(LocalDate.now()) || (localDate.isEqual(LocalDate.now()) && localTime.isBefore(LocalTime.now()))) {
            System.out.println("Cannot make reservation on a time that has passed");
        } else {
            System.out.println(time);
            System.out.println("Please enter the number of pax");
            int pax = in.nextInt();
            System.out.println("Please enter the name of the booker");
            in.nextLine();
            String name = in.nextLine();
            System.out.println("Please enter the contact of the booker");
            String contact = in.nextLine();
            int max = 0;
            if (reservationManager.getReservations() != null) {
                for (Reservation reservation : reservationManager.getReservations()) {
                    if (reservation.getId() > max) {
                        max = (int) reservation.getId();
                    }
                }
            }
            boolean isBookingSuccess = false;
            if (localDate.isEqual(LocalDate.now())) {
                for (Table t : reservationManager.getTables()) {
                    if (!t.isOccupied() && t.getNumOfSeats() >= pax) {
                        System.out.println("Reservation booking success");
                        System.out.println("Table " + t.getId() + " is reserved");
                        System.out.println("Your reservation id is: " + (max + 1));
                        t.setOccupied(true);
                        isBookingSuccess = true;
                        Reservation reservation = new Reservation(max + 1, localDate, localTime, pax, name, contact, t);
                        reservationManager.add(reservation);
                        reservation.setTable(t);
                        reservationManager.getReservations().add(reservation);
                        break;
                    }
                }
            } else {
                List<Reservation> reservations = reservationManager.getReservations();
                List<Table> tables = reservationManager.getTables();
                for (Reservation r : reservations) {
                    if (r.getLocalDate().isEqual(localDate)) {
                        tables.remove(r.getTable());
                    }
                }
                for (Table t : tables) {
                    if (t.getNumOfSeats() >= pax) {
                        System.out.println("Reservation booking success");
                        System.out.println("Table " + t.getId() + " is reserved");
                        System.out.println("Your reservation id is: " + (max + 1));
                        isBookingSuccess = true;
                        Reservation reservation = new Reservation(max + 1, localDate, localTime, pax, name, contact, t);
                        reservationManager.add(reservation);
                        reservation.setTable(t);
                        break;
                    }
                }
            }
            if (!isBookingSuccess) {
                System.out.println("No available table for Reservation today");
            }
        }
    }

    /**
     * Check in to a reservation if allowed. If yes, go to order immediately
     * Clear all expired reservations before checking
     * The reservation is located according to its reservation id
     * It calls the reservationCheckIn method in the reservationManager class to remove the reservation from the reservation.json file
     *
     * @throws IOException Signals that an I/O exception occurs in the reservationManager related to json operation
     */
    public void checkReservation() throws IOException {
        reservationManager.clearExpiredReservations();
        System.out.println("Please enter the reservation id you received when booking");
        int id = in.nextInt();
        reservationManager.reservationCheckIn(id);
    }

    /**
     * Delete a reservation if allowed.
     * Clear all expired reservations before checking
     * The reservation is located according to its reservation id
     * It calls the removeReservation method in the reservationManager class to remove the reservation from the reservation.json file
     *
     * @throws IOException
     */
    public void removeReservation() throws IOException {
        reservationManager.clearExpiredReservations();
        System.out.println("Please enter the reservation id you received when booking");
        int id = in.nextInt();
        reservationManager.delete(id);
    }
}
