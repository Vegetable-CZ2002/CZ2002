package managers;

import beans.Reservation;
import beans.Restaurant;
import beans.Table;

import java.time.LocalTime;
import java.util.ArrayList;


public class ReservationManager {
    static ArrayList<Reservation> reservations= Restaurant.reservations;

    public static void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public static void removeReservation(Reservation reservation){
        boolean removeSuccessful= false;
        for(Reservation r: reservations){
            if(reservation.getName().equals(r.getName())){
                reservations.remove(r);
                removeSuccessful= true;
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove Reservation Failure");
        }
    }

    public static boolean checkReservation(Reservation reservation){
        boolean isReserved= false;
        for(Reservation r: reservations){
            if(reservation.getName().equals(r.getName())){
                isReserved= true;
                break;
            }
        }
        if(!isReserved){
            System.out.println("Is Not Reserved");
        }
        return isReserved;
    }

    public static void checkReservationExpiry(Reservation r){
        if(LocalTime.now().isAfter(r.getTime().	plusMinutes(20))) {
            removeReservation(r);	//Free up table for walk-in customers
	    }
    }



    public static void reservationArrival(int number){
        ArrayList<Table> tables= Restaurant.tables;
        for(Table t: tables){
            if(t.tableReservation!= null){
                if(t.tableReservation.getId()== number) {
                    // 
					System.out.println("Customer Successfully Seated!");
					return;
				}
            }
        }
    }

    public static void clearExpiredReservations(ArrayList<Table> t) {
		for(int i=0;i<t.size();i++) {
			if(t.get(i).tableReservation != null) {
				checkReservationExpiry(t.get(i).tableReservation);
			}
		}
		System.out.println("Expired reservations purged!");
	}


}
