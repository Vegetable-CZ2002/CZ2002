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
 * User interface class for the instructions regarding table
 *
 * @author Ruan Donglin
 */
public class TableUI {
    private final ReservationManager reservationManager;
    private final TableManager tableManager;
    private static final Scanner in = MainUI.in;

    public TableUI() throws IOException {
        this.reservationManager = new ReservationManager();
        this.tableManager = new TableManager();
    }



    /**
     * Check the defined date and time's table availability
     * Check all the reservations in the reservation.json file to find all table occupied at the defined time for reservation
     * Print all tables that are not occupied for reservation
     *
     * @throws IOException Signals that an I/O exception occurs in the reservationManager related to json operation
     */
    void mainUI() throws IOException {
        System.out.println("Please enter the date to check availability in the format of YYYY-MM-DD:(eg. 2021-11-12)");
        in.nextLine();
        String date = in.nextLine();
        LocalDate localDate = LocalDate.parse(date);
        System.out.println(date);
        System.out.println("Please enter the time to check availability in the format of HH:MM(eg. 18:00)");
        String time = in.nextLine();
        LocalTime localTime = LocalTime.parse(time + ":00");
        if (localDate.isBefore(LocalDate.now()) || (localDate.isEqual(LocalDate.now()) && localTime.isBefore(LocalTime.now()))) {
            System.out.println("Cannot check availability on a time that has passed");
        } else {
            List<Table> tableList = tableManager.read();
            if (localTime.isBefore(LocalTime.of(15, 0, 0))) {
                for (Reservation r : reservationManager.getReservations()) {
                    if (r.getLocalDate().isEqual(localDate) && r.getLocalTime().isBefore(LocalTime.of(15, 0, 0))) {
                        for (Table table : tableList) {
                            if (r.getTable().getId() == table.getId()) {
                                tableList.remove(table);
                            }
                        }
                    }
                }
                if (tableList.size() == 0) {
                    System.out.println("No available table");
                } else {
                    for (Table table : tableList) {
                        System.out.println("Table " + table.getId() + " is available");
                    }
                }
            } else {
                List<Table> tableList2 = tableManager.read();
                for (Reservation r : reservationManager.getReservations()) {
                    if (r.getLocalDate().isEqual(localDate)) {
                        for (Table table : tableList2) {
                            if (r.getTable().getId() == table.getId()) {
                                tableList2.remove(table);
                                break;
                            }
                        }
                    }
                }
                if (tableList2.size() == 0) {
                    System.out.println("No available table");
                } else {
                    for (Table table : tableList2) {
                        System.out.println("Table " + table.getId() + " is available");
                    }
                }
            }
        }
    }
}
