package managers;

import beans.Reservation;
import beans.Restaurant;
import beans.Table;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class ReservationManager {
    final static List<Reservation> reservations= Restaurant.reservations;
    final static List<Table> tables= Restaurant.tables;

    public static void addReservation(Reservation reservation){
        boolean emptyTableExist= false;
        for(Table t: tables){
            if(t.isOccupied()== false && t.getTableReservation()== null && t.getNumOfSeats()>= reservation.getPax()){
                reservations.add(reservation);
                t.setOccupied(true);
                reservation.setTable(t);
                t.setTableReservation(reservation);
                emptyTableExist= true;
                break;
            }
        }
        if(!emptyTableExist){
            System.out.println("No Empty Table Available for Reservation!");
        }
    }

    public static void removeReservation(Reservation reservation){
        boolean removeSuccessful= false;
        for(Reservation r: reservations){
            if(reservation.equals(reservation)){
                reservations.remove(r);
                reservation.getTable().setTableReservation(null);
                removeSuccessful= true;
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove Reservation Failure");
        }
    }

    public static boolean checkReservationIfExist(Reservation reservation){
        boolean isReserved= false;
        for(Reservation r: reservations){
            if(reservation.equals(r)){
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
            removeReservation(r);
            r.getTable().setOccupied(false);
            System.out.println("Remove reservation!");
	    }
    }


    public static void reservationCheckIn(Reservation r){
        removeReservation(r);
        r.getTable().setOccupied(true);
    }

    public static void clearExpiredReservations() {
		for(Table t: tables) {
			if(t.getTableReservation() != null) {
				checkReservationExpiry(t.getTableReservation());
			}
		}
		System.out.println("Expired reservations purged!");
	}


}
