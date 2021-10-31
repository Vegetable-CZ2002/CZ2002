package console;

import managers.ReservationManager;

import java.util.Scanner;

public class ReservationUI {
    public static void main(String[] args) {
        int num;
        do{
            System.out.println("Welcome to the restaurant! What action do you wish to do now? ");
            System.out.println("[0] Exit from restaurant");
            System.out.println("[1] Create reservation booking");
            System.out.println("[2] Check reservation booking");
            System.out.println("[3] Remove reservation booking");
            Scanner in = new Scanner(System.in);
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
                default :
            }
        } while(num!= 0);
    }

    public static void createReservation(){

    }
    public static void checkReservation(){
        System.out.println("Please enter the reservation id you received when booking");
        Scanner in= new Scanner(System.in);
        int id= in.nextInt();
        ReservationManager.reservationCheckIn(id);
    }
    public static void removeReservation(){

    }

}
