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

public class TableUI {
    private static Scanner in = MainUI.in;

    public static void checkTableAvailability() throws IOException {
        System.out.println("Please enter the date to check availability in the format of YYYY-MM-DD:(eg. 2021-11-12)");
        in.nextLine();
        String date = in.nextLine();
        LocalDate localDate= LocalDate.parse(date);
        System.out.println(date.toString());
        System.out.println("Please enter the time the date to check availability in the format of HH:MM(eg. 18:00)");
        String time= in.nextLine();
        LocalTime localTime= LocalTime.parse(time+":00");
        if(localDate.isBefore(LocalDate.now()) || (localDate.isEqual(LocalDate.now())&& localTime.isBefore(LocalTime.now()))){
            System.out.println("Cannot check availability on a time that has passed");
        }
        else {
            List<Table> tableList= TableManager.readTable();
            if(localTime.isBefore(LocalTime.of(12,00,00))){
                for(Reservation r: ReservationManager.readReservation()){
                    if(r.getLocalDate().isEqual(localDate) && r.getLocalTime().isBefore(LocalTime.of(12,00,00))){
                        tableList.remove(r.getTable());
                    }
                }
                if(tableList.size()== 0){
                    System.out.println("No available table");
                }
                else{
                    for(Table table: tableList){
                        System.out.println("Table "+ table.getId() + " is available");
                    }
                }
            }
            else{
                System.out.println("Plz");
                List<Table> tableList2= TableManager.readTable();
                for(Reservation r: ReservationManager.readReservation()){
                    if(r.getLocalDate().isEqual(localDate)){
                        System.out.println("Plz2");
                        tableList2.remove(r.getTable());
                    }
                }
                if(tableList2.size()== 0){
                    System.out.println("No available table");
                }
                else{
                    for(Table table: tableList2){
                        System.out.println("Table "+ table.getId() + " is available");
                    }
                }
            }
        }
    }
}
