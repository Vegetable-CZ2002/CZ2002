package console;

import beans.Reservation;
import beans.Table;
import managers.ReservationManager;
import managers.TableManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class ReservationUI {
    //private static Scanner in= new Scanner(System.in);
    private static Scanner in = MainUI.in;
    public static void mainUI() throws IOException {
        int num;
        do{
            System.out.println("Welcome to the reservation section! What action do you wish to take?");
            System.out.println("[0] Exit from reservation section");
            System.out.println("[1] Create reservation booking");
            System.out.println("[2] Check reservation booking");
            System.out.println("[3] Remove reservation booking");
            System.out.println("[4] View reservation booking");
            num= in.nextInt();
            switch (num){
                case 1 :
                    createReservation();
                    break;
                case 2 :
                    checkReservation();
                    break;
                case 3 :
                    removeReservation();
                    break;
                case 4 :
                    viewReservation();
                    break;
                default :
            }
        } while(num!= 0);
    }

    public static void main(String[] args) throws IOException {
        int num;
        do{
            System.out.println("Welcome to the reservation section! What action do you wish to take?");
            System.out.println("[0] Exit from reservation section");
            System.out.println("[1] Create reservation booking");
            System.out.println("[2] Check reservation booking");
            System.out.println("[3] Remove reservation booking");
            System.out.println("[4] View reservation booking");
            num= in.nextInt();
            switch (num){
                case 1 :
                    createReservation();
                    break;
                case 2 :
                    checkReservation();
                    break;
                case 3 :
                    removeReservation();
                    break;
                case 4 :
                    viewReservation();
                    break;
                default :
            }
        } while(num!= 0);
    }

    public static void createReservation() throws IOException {
        ReservationManager.clearExpiredReservations();
        System.out.println("Please enter the following details for reservation ");
        System.out.println("Please enter the reservation date in the format of YYYY-MM-DD:(eg. 2021-11-12)");
        in.nextLine();
        String date = in.nextLine();
        LocalDate localDate= LocalDate.parse(date);
        System.out.println(date.toString());
        System.out.println("Please enter the reservation time in the format of HH:MM(eg. 18:00)");
        String time= in.nextLine();
        LocalTime localTime= LocalTime.parse(time+":00");
        if(localDate.isBefore(LocalDate.now()) || (localDate.isEqual(LocalDate.now())&& localTime.isBefore(LocalTime.now()))){
            System.out.println("Cannot make reservation on a time that has passed");
        }
        else{
            System.out.println(time.toString());
            System.out.println("Please enter the number of pax");
            int pax= in.nextInt();
            System.out.println("Please enter the name of the booker");
            in.nextLine();
            String name= in.nextLine();
            System.out.println("Please enter the contact of the booker");
            String contact= in.nextLine();
            int max= 0;
            if(ReservationManager.readReservation()!= null){
                for(Reservation reservation: ReservationManager.readReservation()){
                    if(reservation.getId()> max){
                        max= (int) reservation.getId();
                    }
                }
            }
            boolean isBookingSuccess= false;
            if(localDate.isEqual(LocalDate.now())){
                for(Table t: TableManager.readTable()){
                    if(t.isOccupied()== false&& t.getNumOfSeats()>= pax){
                        System.out.println("Reservation booking success");
                        System.out.println("Table "+ t.getId()+ " is reserved");
                        System.out.println("Your reservation id is: "+ (max+1));
                        t.setOccupied(true);
                        isBookingSuccess= true;
                        Reservation reservation= new Reservation(max+1, localDate, localTime, pax, name, contact, t);
                        ReservationManager.addReservation(reservation);
                        reservation.setTable(t);
                        ReservationManager.readReservation().add(reservation);
                        break;
                    }
                }
            }
            else{
                List<Reservation> reservations=  ReservationManager.readReservation();
                List<Table> tables= TableManager.readTable();
                for(Reservation r: reservations){
                    if(r.getLocalDate().isEqual(localDate)){
                        tables.remove(r.getTable());
                    }
                }
                for(Table t: tables){
                    if(t.getNumOfSeats()>= pax){
                        System.out.println("Reservation booking success");
                        System.out.println("Table "+ t.getId()+ " is reserved");
                        System.out.println("Your reservation id is: "+ (max+1));
                        isBookingSuccess= true;
                        Reservation reservation= new Reservation(max+1, localDate,localTime,  pax, name, contact, t);
                        ReservationManager.addReservation(reservation);
                        reservation.setTable(t);
                        ReservationManager.readReservation().add(reservation);
                        break;
                    }
                }
            }
            if(!isBookingSuccess){
                System.out.println("No available table for Reservation today");
            }
        }


    }
    public static void checkReservation() throws IOException {
        ReservationManager.clearExpiredReservations();
        System.out.println("Please enter the reservation id you received when booking");
        int id= in.nextInt();
        ReservationManager.reservationCheckIn(id);
    }

    public static void removeReservation() throws IOException {
        ReservationManager.clearExpiredReservations();
        System.out.println("Please enter the reservation id you received when booking");
        int id= in.nextInt();
        ReservationManager.removeReservation(id);
    }

    public static void viewReservation() throws IOException {
        ReservationManager.clearExpiredReservations();
        if(ReservationManager.readReservation().size()== 0){
            System.out.println("No reservation yet");
        }
        else{
            System.out.println("Here's all the reservations in history");
            Formatter fmt = new Formatter();
            fmt.format("%2s  %10s  %5s  %8s  %3s %12s %15s", "id", "Date", "Time", "tableId", "pax", "name", "contact");
            System.out.println(fmt);
            for(Reservation reservation: ReservationManager.readReservation()){
                System.out.println(reservation.formatter());
            }
        }

    }
}
